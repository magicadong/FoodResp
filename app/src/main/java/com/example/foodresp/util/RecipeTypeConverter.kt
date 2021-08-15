package com.example.foodresp.util

import androidx.room.TypeConverter
import com.example.foodresp.data.model.FoodRecipe
import com.example.foodresp.data.model.Result
import com.google.gson.Gson

class RecipeTypeConverter {
    private val gson = Gson()
    //FoodRecipe -> string
    @TypeConverter
    fun foodRecipeToString(recipe:FoodRecipe):String{
        return gson.toJson(recipe)
    }

    //string -> FoodRecipe
    @TypeConverter
    fun stringToFoodRecipe(str:String):FoodRecipe{
        return gson.fromJson(str,FoodRecipe::class.java)
    }

    @TypeConverter
    fun resultToString(recipe: Result):String{
        return gson.toJson(recipe)
    }

    @TypeConverter
    fun string2Result(str:String):Result{
        return  gson.fromJson(str,Result::class.java)
    }
}