package com.example.foodresp.data.locol

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

class LocalRepository(context: Context) {
    private val recipeDao = RecipeDatabase.getInstance(context).getRecipeDao()

    //插入数据
    suspend fun insertRecipe(recipeEntity: RecipeEntity){
        recipeDao.insertRecipe(recipeEntity)
    }

    //查询数据
    fun getRecipes(type:String): Flow<List<RecipeEntity>> {
        return recipeDao.getRecipes(type)
    }

    //更新数据
    suspend fun updateRecipe(recipeEntity: RecipeEntity){
        recipeDao.updateRecipe(recipeEntity)
    }
}