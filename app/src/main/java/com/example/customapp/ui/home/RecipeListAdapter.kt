package com.example.customapp.ui.home


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity

import androidx.activity.result.contract.ActivityResultContracts

import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.customapp.*
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import java.security.AccessController.getContext


class RecipeListAdapter(val resultContract: ActivityResultLauncher<Intent>) : RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recipe_layout, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = Recipes.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = Recipes.recipes[position]
        holder.bind(item)
        if (position%2 == 1){
            holder.itemView.setBackgroundColor(Color.parseColor("#e4acfa"))
        }else{
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
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
                resultContract.launch(intent)
            }
        }
    }

}