package com.example.foodresp.util

import android.content.Context
import android.widget.Toast

fun showToast(context: Context,message:String){
    Toast.makeText(
        context,"Get Recipes Failed:$message", Toast.LENGTH_LONG)
        .show()
}