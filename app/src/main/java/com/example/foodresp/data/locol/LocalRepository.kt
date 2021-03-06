package com.example.foodresp.data.locol

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.foodresp.data.locol.entity.FavoriteEntity
import com.example.foodresp.data.locol.entity.RecipeEntity
import com.example.foodresp.data.model.Result
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

    //查询所有收藏的食谱
    fun getAllFavorites(): Flow<List<FavoriteEntity>> {
        return recipeDao.getAllFavorites()
    }

    //插入收藏食谱
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity){
        recipeDao.insertFavorites(favoriteEntity)
    }

    //删除收藏
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity){
        recipeDao.deleteFavorite(favoriteEntity)
    }
}