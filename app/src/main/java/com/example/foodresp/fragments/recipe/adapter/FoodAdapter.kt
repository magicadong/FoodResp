package com.example.foodresp.fragments.recipe.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.foodresp.R
import com.example.foodresp.data.model.Result
import com.example.foodresp.databinding.FoodItemBinding
import com.example.foodresp.fragments.recipe.RecipeFragmentDirections

class FoodAdapter: RecyclerView.Adapter<FoodAdapter.MyViewHolder>(){
    private var recipeList:List<Result> = emptyList()

    class MyViewHolder(val binding:FoodItemBinding):RecyclerView.ViewHolder(binding.root){
        companion object{
            fun from(parent: ViewGroup):MyViewHolder{
                val inflator = LayoutInflater.from(parent.context)
                val binding = FoodItemBinding.inflate(inflator)
                return MyViewHolder(binding)
            }
        }
        fun bind(result: Result){
            binding.result = result
            binding.executePendingBindings()
            binding.foodContainer.setOnClickListener {
                val action = RecipeFragmentDirections
                    .actionRecipeFragmentToDetailFragment(result)
                binding.foodContainer.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(recipeList[position])
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    fun setData(newData:List<Result>){
        recipeList = newData
        notifyDataSetChanged()
    }
}