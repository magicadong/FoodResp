package com.example.foodresp.data.locol.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodresp.data.model.FoodRecipe

@Entity(tableName = "foodRecipeTable")
class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val type:String,
    val recipe:FoodRecipe
)