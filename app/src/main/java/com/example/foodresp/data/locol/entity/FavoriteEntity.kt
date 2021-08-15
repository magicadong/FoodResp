package com.example.foodresp.data.locol.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodresp.data.model.Result

@Entity(tableName = "favorite_table")
class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var result: Result
)