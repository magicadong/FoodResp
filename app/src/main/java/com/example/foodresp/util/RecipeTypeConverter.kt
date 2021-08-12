package com.example.foodresp.util

import androidx.room.TypeConverter
import com.example.foodresp.data.model.FoodRecipe
import com.google.gson.Gson

class RecipeTypeConverter {
    //FoodRecipe -> string
    @TypeConverter
    fun foodRecipeToString(recipe:FoodRecipe):String{
        return Gson().toJson(recipe)
    }

    //string -> FoodRecipe
    @TypeConverter
    fun stringToFoodRecipe(str:String):FoodRecipe{
        return Gson().fromJson(str,FoodRecipe::class.java)
    }
}