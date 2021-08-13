package com.example.foodresp.fragments.recipe.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodresp.data.model.ExtendedIngredient
import com.example.foodresp.databinding.FragmentIngredientBinding
import com.example.foodresp.databinding.IngredientItemBinding

class IngredientAdapter:RecyclerView.Adapter<IngredientAdapter.MyViewHolder>() {
    private var ingredientList:List<ExtendedIngredient> = emptyList()

    class MyViewHolder(val binding: IngredientItemBinding)
        :RecyclerView.ViewHolder(binding.root){
        companion object{
            fun from(parent: ViewGroup):MyViewHolder{
                val inflator = LayoutInflater.from(parent.context)
                val binding = IngredientItemBinding.inflate(inflator)
                return MyViewHolder(binding)
            }
        }

        fun bind(ingredient: ExtendedIngredient){
            binding.ingredient = ingredient
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ingredient = ingredientList[position]
        Log.v("pxd","配料url:${ingredient.image}")
        holder.bind(ingredient)
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    fun setData(newData:List<ExtendedIngredient>){
        ingredientList = newData
        notifyDataSetChanged()
    }
}