package com.example.customapp

import androidx.lifecycle.MutableLiveData

object Recipes {
    var recipes = mutableListOf<Recipe>()
    //var recipes = MutableLiveData<List<Recipe>>()
    fun getCount(): Int {
        return recipes.size
    }

    fun add(item: Recipe){
        recipes.add(item)
    }

    fun update(item: Recipe){
        for (existingRecipe : Recipe in recipes){
            if (existingRecipe.id == item.id){
                existingRecipe.name = item.name
                existingRecipe.desc = item.desc
                existingRecipe.ingr = item.ingr
                existingRecipe.step = item.step
                existingRecipe.time = item.time
            }
        }
    }
    fun delete(item: Recipe) {
        recipes.remove(item)
    }
}



