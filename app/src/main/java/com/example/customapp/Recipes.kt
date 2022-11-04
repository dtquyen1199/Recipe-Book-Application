package com.example.customapp

import androidx.lifecycle.MutableLiveData

object Recipes {
    var recipes = mutableListOf<Recipe>()
    //var recipes = MutableLiveData<List<Recipe>>()
    fun getCount(): Int {
        return recipes.size
    }


    /*
    fun update(item: Recipe){
        for (var existingRecipe : Recipe in recipes){
            if (existingRecipe.id == item.id){
                existingRecipe = item
            }
        }

        if (true){
            //if item id already exist in here
            item
        }else{
            //outside of range - add to this.
        }

    }

     */
}



