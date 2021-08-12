package com.example.foodresp.fragments.recipe

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

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
}