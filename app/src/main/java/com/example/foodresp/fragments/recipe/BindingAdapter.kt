package com.example.foodresp.fragments.recipe

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.foodresp.R
import com.example.foodresp.data.model.Result
import com.google.android.material.chip.Chip

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("loadImageWithUrl")
    fun loadImageWithUrl(imageView:ImageView,url:String){
        //将url对应的图片下载下来 显示到imageView上
        //Glide
        Glide.with(imageView.context)
            .load(url)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("loadIngredientImageWithName")
    fun loadIngredientImageWithName(imageView:ImageView,name:String){
        //将url对应的图片下载下来 显示到imageView上
        //Glide
        //https://spoonacular.com/cdn/ingredients_250x250/
        val imageBaseUrl = "https://spoonacular.com/cdn/ingredients_250x250/"
        Glide.with(imageView.context)
            .load(imageBaseUrl+name)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("changeStatus")
    fun changeStatus(textView: TextView, status:Boolean){
        textView.isSelected = status
    }

    @JvmStatic
    @BindingAdapter("navigateToDetail")
    fun navigateToDetail(view: View,result: Result){
        val header = view.findViewById<ImageView>(R.id.header)
        header.findNavController().navigate(R.id.action_recipeFragment_to_detailFragment)
    }
}