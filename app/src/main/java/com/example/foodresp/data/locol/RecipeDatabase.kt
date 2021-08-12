package com.example.foodresp.data.locol

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodresp.util.RecipeTypeConverter

@TypeConverters(RecipeTypeConverter::class)
@Database(entities = [RecipeEntity::class],version = 1,exportSchema = false)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun getRecipeDao():RecipeDao

    companion object{
        private var instance:RecipeDatabase? = null
        fun getInstance(context: Context):RecipeDatabase{
            if (instance != null){
                return instance!!
            }
            synchronized(this){
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context,RecipeDatabase::class.java,"food_recipe.db"
                    ).build()
                }
                return instance!!
            }
        }
    }
}