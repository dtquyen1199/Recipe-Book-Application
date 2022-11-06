package com.example.customapp.ui.shoppinglist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.customapp.R
import com.example.customapp.Recipe
import com.example.customapp.RecipeDetailActivity
import com.example.customapp.Recipes

class IngredientListAdapter(private val ingredientList: List<String>) :  RecyclerView.Adapter<IngredientListAdapter.ViewHolder>(){
    //lateinit var ingredientList: MutableList<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientListAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.ingredient_layout, parent, false) as View
        return ViewHolder(view)
    }

    //get that list!!!


    override fun getItemCount(): Int = ingredientList.size

    override fun onBindViewHolder(holder: IngredientListAdapter.ViewHolder, position: Int) {

        //val item = Recipes.recipes.value!![position]
        val item = ingredientList[position]
        holder.bind(item)
    }

    inner class ViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        val ingredient = v.findViewById<TextView>(R.id.ingr)

        fun bind(item: String) {
            ingredient.text = item


            /*
            v.setOnClickListener {
                val activity = v!!.context as AppCompatActivity
                val intent = Intent(activity, RecipeDetailActivity::class.java).apply{
                    putExtra("recipe",item)
                }
                activity.startActivity(intent)
            }


             */

        }

    }

}
