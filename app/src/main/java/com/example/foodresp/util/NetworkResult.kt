package com.example.foodresp.util

import com.example.foodresp.data.model.FoodRecipe

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null){

    class Success<T>(data: T):NetworkResult<T>(data)
    class Error<T>(errMsg:String):NetworkResult<T>(message = errMsg)
    class Loading<T>():NetworkResult<T>()
}