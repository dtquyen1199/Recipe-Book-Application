package com.example.customapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.customapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val db = DBHelper(this, null)

        //val aRecipe = Recipe(0,"Fried Chicken","This is a recipe for fried chicken")
        //db.addRecipe(aRecipe)

        Recipes.recipes = db.readData()

        //val db = DBHelper(this, null)
        //val cursor  = db.getName()

        //cursor!!.moveToFirst()
        //Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
        //Description.append(cursor.getString(cursor.getColumnIndex(DBHelper.DESC_COL)) + "\n")

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
            //new recipe here
            val newRecipe = Recipe()
            val intent = Intent(this, RecipeDetailActivity::class.java).apply{
                putExtra("recipe",Recipe())
            }
            startActivity(intent)
        }


    }
}