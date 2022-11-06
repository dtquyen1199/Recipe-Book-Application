package com.example.customapp

import android.app.AlertDialog
import android.content.DialogInterface
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
        //fix this
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

        // implement this later
        /*val imageState: Int = when (index) {
            0 -> R.drawable.futurenostalgia
            1 -> R.drawable.chromatica
            2 -> R.drawable.disco
            3 -> R.drawable.norman
            else -> {
                R.drawable.ic_launcher_foreground
            }
        }
        binding.image.setImageDrawable(getDrawable(imageState))

         */

    }



    /*
    private fun validFields(): Boolean {
        return (validateTitle() && validateArtist() && validateLocation() && validateDate())
    }

    private fun validateTitle(): Boolean {
        if (binding.title.text.isNullOrBlank()) {
            binding.titleLayout.helperText = "Please do not leave title blank"
            return false
        }
        return true
    }

    private fun validateArtist(): Boolean {
        if (binding.artist.text.isNullOrBlank()) {
            binding.artistLayout.helperText = "Please do not leave artist blank"
            return false
        }
        return true
    }

    private fun validateLocation(): Boolean {
        if (binding.location.text.isNullOrBlank()) {
            binding.locationLayout.helperText = "Please do not leave location blank"
            return false
        }
        return true
    }

    private fun validateDate(): Boolean {
        val dateFields = binding.date.text.toString().split("/")
        for (field in dateFields) {
            if (field.isNullOrBlank()) {
                binding.dateLayout.helperText = "Please fill out the date"
                return false
            }
        }
        return true
    }

     */
    fun changed(userInputRecipe: Recipe): Boolean {
        //return recipe.name != userInputRecipe.name || recipe.desc != userInputRecipe.desc
        return recipe != userInputRecipe
    }
    fun showErrorHandlingDialog(){
        //show the dialog here
        ErrorHandlingDialog(this).show("Continue editing?",
            "Some Fields are empty") {
            Toast.makeText(applicationContext,
                it.toString(),
                Toast.LENGTH_LONG).show()
        }
        val builder = AlertDialog.Builder(this)
    }

    fun showDialog(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Continue Editing?")
        builder.setMessage("Some fields are left empty")
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
        builder.setPositiveButton("Yes, Keep editing",KeepEditClickListener())

        //Discard the edit
        builder.setNegativeButton("No, discard", DiscardEditListener())

        val alertDialog: AlertDialog = builder.create()

        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
    override fun onBackPressed() {
        //if (validFields()) {
        //$ID_COL, $NAME_COL, $DESC_COL, $INGR_COL, $STEP_COL, $TIME_COL

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
                    //make changes to the DB.

                    if (userInputRecipe.id == -1){
                        //add new recipe
                        db.addRecipe(userInputRecipe)
                    }
                    else{
                        //update existing recipe
                        db.update(userInputRecipe)
                    }

                    //db.addRecipe(userInputRecipe)
                    //update the RecipesSingleton
                    //Recipes.update(userInputRecipe)


                    /*
                    val intent = Intent().apply {
                        putExtra("recipe", )
                        putExtra("index", index)
                    }
                    val randName = intent.getParcelableExtra<Album>("album")
                    */

                    setResult(RESULT_OK, intent)
                    val toast = Toast.makeText(applicationContext, "Changes Saved", Toast.LENGTH_SHORT)
                    toast.show()
                    finish()
                } else {
                    setResult(RESULT_CANCELED)
                    finish()
                }

            }
        //} else {
        //}
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
                db.delete(recipe)
                setResult(RESULT_CANCELED)
                val toast = Toast.makeText(applicationContext, "Recipe Deleted", Toast.LENGTH_SHORT)
                toast.show()
                finish()
                true
            }
            R.id.create_shopping_list -> {
                //delete item here

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