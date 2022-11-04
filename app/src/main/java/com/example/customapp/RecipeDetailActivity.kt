package com.example.customapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.MediatorLiveData
import com.example.customapp.databinding.ActivityRecipeDetailBinding

class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var recipe : Recipe
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

    override fun onBackPressed() {
        //if (validFields()) {
        //$ID_COL, $NAME_COL, $DESC_COL, $INGR_COL, $STEP_COL, $TIME_COL
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
                var db  = DBHelper(this, null)
                //db.addRecipe(userInputRecipe)
                db.update(userInputRecipe)
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
        //} else {
        //}
    }
}