package com.example.customapp.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customapp.*
import com.example.customapp.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    var db = DBHelper

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //ex 2 starts: update the adapter instead of recreating one every time
    lateinit var recipeListRecView :RecyclerView
    //ex 2 ends
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        recipeListRecView = root.findViewById<RecyclerView>(R.id.recipelist)

        recipeListRecView.adapter = RecipeListAdapter()
        recipeListRecView.layoutManager = LinearLayoutManager(activity)

        /*
        homeViewModel.liveDataRecipes.observe(viewLifecycleOwner){

            /*
            //here is where you update the value :) but you can't, cuz it's not just 1 or 2 values that you can update straight away
            // i need to overcome this - by potentially make

            //Apporach 1:
            - Notify the adapter of every changes.
            - Question: Does the Recipes singleton change with the thing? or no??
             */
            recipeListAdapter.notifyDataSetChanged()
        }

         */

        //This seems cool but idk how to use it yet
        /*val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
         */

        /*
        val image: ImageView = binding.image
        image.setOnClickListener(){
            val intent = Intent(activity,RecipeDetailActivity::class.java).apply{
                putExtra("recipe",Recipes.recipes[0])
            }
            startActivity(intent)
        }

        if (Recipes.recipes.isNotEmpty()) {
            val name: TextView = binding.name
            name.text = Recipes.recipes[0].name
            val desc: TextView = binding.desc
            desc.text = Recipes.recipes[0].desc
        }
         */
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}