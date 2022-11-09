package com.example.customapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.customapp.databinding.ActivityRecipeDetailBinding
import com.example.customapp.ui.shoppinglist.ShoppingListFragment


class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var recipe : Recipe

    private lateinit var db : DBHelper
    private lateinit var binding: ActivityRecipeDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        recipe = intent.getParcelableExtra<Recipe>("recipe")!!
        val index = intent.getIntExtra("index", 0)
        db  = DBHelper(this, null)
        //display values
        recipe.let {
            binding.name.setText(it.name)
            binding.desc.setText(it.desc)
            binding.ingr.setText(it.ingr)
            binding.step.setText(it.step)
            binding.time.setText(it.time.toString())
        }
    }

    fun changed(userInputRecipe: Recipe): Boolean {
        //return recipe.name != userInputRecipe.name || recipe.desc != userInputRecipe.desc
        return recipe != userInputRecipe
    }

    fun showDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Discard Edit?")
        builder.setMessage("Some fields are left empty. Changes will not be saved.")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        class KeepEditClickListener : DialogInterface.OnClickListener   {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                //Stay in activity - keep editing
            }
        }
        class DiscardEditListener : DialogInterface.OnClickListener   {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                finish()
            }
        }
        //keep editing, stay in acitivy
        builder.setNegativeButton("No, keep editing",KeepEditClickListener())
        //Discard the edit
        builder.setPositiveButton("Yes, discard", DiscardEditListener())

        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(true)
        alertDialog.show()
    }

    override fun onBackPressed() {
            if (binding.name.text.isNullOrBlank() || binding.desc.text.isNullOrBlank() || binding.ingr.text.isNullOrBlank() || binding.step.text.isNullOrBlank() || binding.time.text.isNullOrBlank() )
            {
                showDialog()
            }
            else {
                val userInputRecipe = Recipe(
                    recipe.id,
                    binding.name.text.toString(),
                    binding.desc.text.toString(),
                    binding.ingr.text.toString(),
                    binding.step.text.toString(),
                    binding.time.text.toString().toInt()
                )
                if (changed(userInputRecipe)) {
                    val intent = Intent().apply{
                        putExtra("recipe",userInputRecipe)
                    }
                    //make changes to the DB.
                    if (userInputRecipe.id == -1){
                        //add new recipe
                        db.addRecipe(userInputRecipe)
                        setResult(1, intent)
                    }
                    else{
                        //update existing recipe
                        db.update(userInputRecipe)
                        setResult(3, intent)
                    }
                    val toast = Toast.makeText(applicationContext, "Changes Saved", Toast.LENGTH_SHORT)
                    toast.show()
                    finish()
                } else {
                    setResult(RESULT_CANCELED)
                    finish()
                }
            }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.delete -> {
                //delete item here
                val intent = Intent().apply{
                    putExtra("recipe",recipe)
                }
                db.delete(recipe)
                val toast = Toast.makeText(applicationContext, "Recipe Deleted", Toast.LENGTH_SHORT)
                toast.show()
                setResult(4,intent)
                finish()
                true
            }
            R.id.create_shopping_list -> {
                //start Shopping List Fragment
                val supportManager = supportFragmentManager
                val fragmentTransaction = supportManager.beginTransaction()
                val shoppingListFragment = ShoppingListFragment()
                val bundle = Bundle()
                bundle.putString("ingredients",recipe.ingr)
                shoppingListFragment.arguments = bundle
                fragmentTransaction.add(R.id.fragment_container,shoppingListFragment)
                fragmentTransaction.commit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}