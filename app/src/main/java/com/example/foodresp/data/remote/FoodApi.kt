package com.example.foodresp.data.remote

import com.example.foodresp.data.model.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {
    //基地址 https://api.spoonacular.com/
    @GET("recipes/complexSearch?addRecipeInformation=true&fillIngredients=true&apiKey=1a0edebda73f4a17ad82375357e41313")
    suspend fun fetchFoodRecipes(@Query("type")type:String):Response<FoodRecipe>
}