package com.example.customapp.ui.shoppinglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.customapp.R
import com.example.customapp.databinding.FragmentShoppingListBinding

class ShoppingListFragment : Fragment() {

    private var _binding: FragmentShoppingListBinding? = null
    lateinit var ingredientList : List<String>
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(ShoppingListViewModel::class.java)

        _binding = FragmentShoppingListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val text : TextView = binding.text
        val data = arguments
        if (data != null) {
            val tempString = data.getString("ingredients")
            if (tempString != null) {
                ingredientList = tempString.split('\n')
            }
            val ingredientListRecyclerView = root.findViewById<RecyclerView>(R.id.ingredient_list)
            ingredientListRecyclerView.adapter = IngredientListAdapter(ingredientList)
            ingredientListRecyclerView.layoutManager = LinearLayoutManager(activity)
        }
        else {
            text.text = "No Item in the shopping list"
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}