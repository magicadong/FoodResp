package com.example.foodresp.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodresp.data.locol.LocalRepository
import com.example.foodresp.data.locol.RecipeEntity
import com.example.foodresp.data.model.FoodRecipe
import com.example.foodresp.data.remote.FoodApi
import com.example.foodresp.data.remote.RemoteRepository
import com.example.foodresp.util.NetworkResult
import com.example.foodresp.util.showToast
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class MainViewModel(application: Application):AndroidViewModel(application) {
    //网络请求对象
    private val remoteRepository = RemoteRepository()
    //数据库的操作对象
    private val localRepository = LocalRepository(getApplication())
    //需要给外部观察
    var recipes:MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()

    //外部通过这个方法发起网络请求
    fun fetchFoodRecipes(type:String){
        //处于loading状态
        recipes.value = NetworkResult.Loading()

        //判断网络是否有连接
        if (hasInternetConnection()) {
            viewModelScope.launch {
                //先读取数据库的数据
                val result = localRepository.getRecipes(type)
                result.collect {
                    if (it.isNotEmpty()) {
                        val entity = it.first()
                        val data = entity.recipe
                        recipes.value = NetworkResult.Success(data)
                    } else {
                        try {
                            val response = remoteRepository.fetchFoodRecipes(type)
                            if (response.isSuccessful) {
                                //获取数据成功 处于success状态
                                recipes.value = NetworkResult.Success(response.body()!!)
                                //需要将数据保存到数据库
                                localRepository.insertRecipe(RecipeEntity(0,type,response.body()!!))
                            } else {
                                //获取数据失败 处于error状态
                                recipes.value = NetworkResult.Error(response.message())
                            }
                        }catch (e:Exception){
                            recipes.value = NetworkResult.Error("time out:${e.message}")
                        }
                    }
                }
            }
        }else{
            //从数据库读取数据
            viewModelScope.launch {
                val result = localRepository.getRecipes(type)
                result.collect {
                    if (it.isNotEmpty()) {
                        val entity = it.first()
                        val data = entity.recipe
                        recipes.value = NetworkResult.Success(data)
                    }else{
                        recipes.value = NetworkResult.Error("Empty")
                    }
                }
            }
        }
    }

    //判断是否有网络连接
    private fun hasInternetConnection():Boolean{
        //获取系统的网络连接管理器
        val connectivityManager = getApplication<Application>()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capability = connectivityManager
            .getNetworkCapabilities(activeNetwork) ?: return false

        return when{
            capability.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capability.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capability.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}