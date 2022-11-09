package com.example.customapp.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
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
    private val binding get() = _binding!!
    lateinit var recipeListRecView :RecyclerView
    lateinit var root : View

    lateinit var fab: View
    val resultContract =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val intent = result.data
            val item = intent?.getParcelableExtra<Recipe>("recipe")
            if (item != null){
                when (result.resultCode) {
                    //C(R)UD
                    1 -> addItem(item)
                    3 -> updateItem(item)
                    4 -> deleteItem(item)
                    else -> {
                    }
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        root = binding.root

        fab = root.findViewById(com.example.customapp.R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
            val activity = root!!.context as AppCompatActivity
            val intent = Intent(activity, RecipeDetailActivity::class.java).apply{
                putExtra("recipe",Recipe())
            }
            resultContract.launch(intent)
        }

        recipeListRecView = root.findViewById<RecyclerView>(R.id.recipelist)
        recipeListRecView.adapter = RecipeListAdapter(resultContract)
        recipeListRecView.layoutManager = LinearLayoutManager(activity)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addItem(item: Recipe){
        Recipes.add(item)
        recipeListRecView.adapter?.notifyItemInserted(Recipes.getCount())
    }

    private fun deleteItem(item: Recipe){
        Recipes.delete(item)
        recipeListRecView.adapter?.notifyDataSetChanged()
    }

    private fun updateItem(item: Recipe) {
        Recipes.update(item)
        recipeListRecView.adapter?.notifyDataSetChanged()
    }
}