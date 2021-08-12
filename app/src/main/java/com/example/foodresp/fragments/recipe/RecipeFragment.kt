package com.example.foodresp.fragments.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodresp.R
import com.example.foodresp.databinding.FragmentRecipeBinding
import com.example.foodresp.fragments.recipe.adapter.FoodAdapter
import com.example.foodresp.fragments.recipe.adapter.TypeAdapter
import com.example.foodresp.util.NetworkResult
import com.example.foodresp.util.showToast
import com.example.foodresp.viewmodel.MainViewModel

class RecipeFragment : Fragment() {
    private lateinit var binding:FragmentRecipeBinding
    private val typeAdapter = TypeAdapter()
    private val foodAdapter = FoodAdapter()
    private val mainViewModel:MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBinding.inflate(inflater)
        initTypeRecyclerView()
        initFoodRecyclerView()
        mainViewModel.recipes.observe(viewLifecycleOwner){
            when(it){
                is NetworkResult.Success -> {
                    binding.foodRecyclerView.hideShimmer()
                    foodAdapter.setData(it.data!!.results) }
                is NetworkResult.Loading -> {
                    binding.foodRecyclerView.showShimmer() }
                is NetworkResult.Error ->{
                    binding.foodRecyclerView.hideShimmer()
                    showToast(requireContext(),it.message!!)
                }
            }
        }
        fetchData("main course")

        return binding.root
    }
    private fun initFoodRecyclerView(){
        binding.foodRecyclerView.layoutManager = GridLayoutManager(
            requireContext(),2)
        binding.foodRecyclerView.adapter = foodAdapter
    }

    private fun initTypeRecyclerView(){
        //配置类型选择的recyclerView
        binding.typeRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),RecyclerView.HORIZONTAL,false)
        binding.typeRecyclerView.adapter = typeAdapter
        //处理回调事件
        typeAdapter.callBack = { current, last ->
            val currentHolder = binding.typeRecyclerView
                .findViewHolderForAdapterPosition(current) as TypeAdapter.MyViewHolder
            val lastHolder = binding.typeRecyclerView
                .findViewHolderForAdapterPosition(last)
            //选中当前
            currentHolder.changeSelectedStatus(true)
            if (lastHolder != null){
                val lastTypeHolder = lastHolder as TypeAdapter.MyViewHolder
                //取消选中原先
                lastTypeHolder.changeSelectedStatus(false)
            }else{
                //重新把上一次选中的item刷新
                typeAdapter.notifyItemChanged(last)
            }

            //获取数据
            fetchData(typeAdapter.typeList[current])
        }
    }

    private fun fetchData(type:String){
        mainViewModel.fetchFoodRecipes(type)
    }
}













