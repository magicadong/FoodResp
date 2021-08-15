package com.example.foodresp.data.locol.dao

import androidx.room.*
import com.example.foodresp.data.locol.entity.FavoriteEntity
import com.example.foodresp.data.locol.entity.RecipeEntity
import com.example.foodresp.data.model.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    //插入数据
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipeEntity: RecipeEntity)

    //查询数据
    @Query("select * from foodRecipeTable where type=:type")
    fun getRecipes(type:String):Flow<List<RecipeEntity>>

    //更新数据
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRecipe(recipeEntity: RecipeEntity)

    /**-----favorite-------*/
    @Query("select * from favorite_table order by id asc")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorites(favoritesEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favoritesEntity: FavoriteEntity)
}