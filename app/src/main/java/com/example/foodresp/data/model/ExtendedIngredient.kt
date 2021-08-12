package com.example.foodresp.data.model


import com.google.gson.annotations.SerializedName

data class ExtendedIngredient(
    @SerializedName("aisle")
    val aisle: String,
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("consistency")
    val consistency: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("unit")
    val unit: String
)