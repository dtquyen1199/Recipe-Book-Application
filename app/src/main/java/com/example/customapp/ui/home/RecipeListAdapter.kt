package com.example.customapp.ui.home


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import androidx.activity.result.contract.ActivityResultContracts

import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.customapp.*
import com.google.android.material.internal.ContextUtils.getActivity
import java.security.AccessController.getContext

//class RecipeListAdapter() : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

//ex2: update the adapter
class RecipeListAdapter() : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {
//ex2 ends
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recipe_layout, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = Recipes.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //val item = Recipes.recipes.value!![position]
        val item = Recipes.recipes[position]
        holder.bind(item)
    }

    inner class ViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
        val name = v.findViewById<TextView>(R.id.name)
        val desc = v.findViewById<TextView>(R.id.desc)

        fun bind(item: Recipe) {
            name.text = item.name
            desc.text = item.desc

            v.setOnClickListener {
                val activity = v!!.context as AppCompatActivity
                val intent = Intent(activity, RecipeDetailActivity::class.java).apply{
                    putExtra("recipe",item)
                }
                activity.startActivity(intent)
            }

        }

    }


}