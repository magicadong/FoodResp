package com.example.foodresp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodresp.data.model.FoodRecipe
import com.example.foodresp.data.remote.FoodApi
import com.example.foodresp.data.remote.RemoteRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(application: Application):AndroidViewModel(application) {
    //网络请求对象
    private val remoteRepository = RemoteRepository()
    //需要给外部观察
    var recipes:MutableLiveData<FoodRecipe> = MutableLiveData()

    //外部通过这个方法发起网络请求
    fun fetchFoodRecipes(type:String){
        viewModelScope.launch {
            val response = remoteRepository.fetchFoodRecipes(type)
            if (response.isSuccessful){
                recipes.value = response.body()
            }
        }
    }
}