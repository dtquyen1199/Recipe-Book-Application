package com.example.customapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.media.audiofx.AudioEffect.Descriptor
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.material.internal.ContextUtils.getActivity


class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            val query0 = ("CREATE TABLE " + TABLE_NAME + " ("
                    + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAME_COL + " TEXT, "
                    + DESC_COL + " TEXT, "
                    + INGR_COL + " TEXT, "
                    + STEP_COL + " TEXT, "
                    + TIME_COL + " INTEGER" + ")")

            db.execSQL(query0)

        //$ID_COL, $NAME_COL, $DESC_COL, $INGR_COL, $STEP_COL, $TIME_COL


        val step1 = "STEP 1\n" +
                "Heat the oil in a large pan and add the onion, celery and carrot. Fry over a medium heat for 10 mins, stirring now and then, until softened and starting to colour.\n" +
                "\n" +
                "STEP 2\n" +
                "Stir in the mince and cook, breaking up any clumps of meat with a wooden spoon, until browned.\n" +
                "\n" +
                "STEP 3\n" +
                "Add the tomato purée, garlic and thyme, and cook for 1-2 mins more. Pour in the wine, if using, and increase the heat to boil off most of the alcohol. Reduce the heat, stir in the stock and season. Cover with a tight-fitting lid and leave to cook gently for 1 hr-1 hr 15 mins until the meat is tender and the sauce has thickened.\n" +
                "\n" +
                "STEP 4\n" +
                "Remove the lid and continue cooking for 15 mins. Meanwhile, cook the pasta following pack instructions. Reserve a mugful of the cooking water, then drain the spaghetti and add to the ragu with the Parmesan. Toss well and add a little pasta water to help the sauce coat the spaghetti. Serve with a side salad and extra cheese, if you like."


        val ingr1 = "1 tbsp olive oil\n" +
                "1 onion, halved and finely chopped\n" +
                "1 celery stick, finely diced\n" +
                "1 large carrot, finely diced\n" +
                "600g pack minced beef steak\n" +
                "3 tbsp tomato purée\n" +
                "2 garlic cloves, finely grated\n" +
                "2 tsp fresh thyme leaves\n" +
                "150ml red wine (or use extra beef stock)\n" +
                "500ml beef stock\n" +
                "400g spaghetti\n" +
                "50g grated parmesan, plus extra to serve (optional)\n" +
                "side salad, to serve"

        val insertQuery1 = ("INSERT INTO $TABLE_NAME ($ID_COL, $NAME_COL, $DESC_COL, $INGR_COL, $STEP_COL, $TIME_COL) VALUES (1,'Ragu Sauce','This is a Ragu Recipe','$ingr1','$step1',60);")

        db.execSQL(insertQuery1)

        val ingr2 = "10 pounds beef bones\n" +
                "2 medium yellow onions - peel and quarter\n" +
                "2 whole heads garlic - halved crosswise\n" +
                "4 (2-inch pieces) ginger - sliced lengthwise\n" +
                "6 whole star anise\n" +
                "12 whole cloves\n" +
                "2 whole cinnamon sticks\n" +
                "2 black cardamom pods\n" +
                "2 tablespoon fennel seeds\n" +
                "2 tablespoon coriander seeds\n" +
                "2 tablespoon salt - (plus more to taste)\n" +
                "⅔ cup fish sauce - divided between the two pots\n" +
                "4 tablespoon sugar - divided between the two pots"

        val step2 = "Cook the mirepoix. To a medium stockpot over medium heat, add the olive oil and shallots. Cook the shallots for approximately 5 minutes, stirring often. Add the white parts of a few green onions, the minced garlic, and ginger. Stir well to combine and cook for 1-2 more minutes.\n" +
                "Add pre-made broth or stock. Add your favorite store-bought broth or stock to the pot and bring to a simmer.\n" +
                "Add some flavor. Add 1-2 whole star anise, soy sauce, and fish sauce to the broth. Cover. Simmer for 15-20 minutes.\n" +
                "Remove the star anise. Try not to forget this part.\n" +
                "Season to taste. When it comes to Pho, you do you. Add more salt, more sugar, more fish sauce, etc."

        val insertQuery2 = ("INSERT INTO $TABLE_NAME ($ID_COL, $NAME_COL, $DESC_COL, $INGR_COL, $STEP_COL, $TIME_COL) VALUES (2,'Pho','Hearty Recipe for a winter day','$ingr2','$step2',120);")
        db.execSQL(insertQuery2)
    }

    fun count(){
        val db = this.readableDatabase
        db.execSQL("SELECT COUNT(*) FROM $TABLE_NAME")
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + "recipe")
        onCreate(db)
    }
    fun addRecipe(userInputRecipe : Recipe){
        val values = ContentValues()
        values.put(NAME_COL, userInputRecipe.name)
        values.put(DESC_COL, userInputRecipe.desc)
        values.put(INGR_COL, userInputRecipe.ingr)
        values.put(STEP_COL, userInputRecipe.step)
        values.put(TIME_COL, userInputRecipe.time)
        val db = this.writableDatabase
        val result = db.insert(TABLE_NAME, null, values)
        if (result == -1.toLong()){
        }else{
        }
        db.close()
    }

    fun readData(): MutableList<Recipe>{
        var list : MutableList<Recipe> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()){
            do {
                var recipe = Recipe(result.getInt(0), result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5) )
                list.add(recipe)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

    fun update(recipe : Recipe){
        val values = ContentValues()
        values.put(NAME_COL, recipe.name)
        values.put(DESC_COL, recipe.desc)
        values.put(INGR_COL, recipe.ingr)
        values.put(STEP_COL, recipe.step)
        values.put(TIME_COL, recipe.time)
        val db = this.writableDatabase
        db.update(TABLE_NAME, values, "$ID_COL = ?", arrayOf(recipe.id.toString()))
    }

    fun delete(recipe : Recipe){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$ID_COL = ?", arrayOf(recipe.id.toString()))
    }

    companion object{
        private val DATABASE_NAME = "RECIPES_APP_DB"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "Recipe"
        val ID_COL = "id"
        val NAME_COL = "name"
        val DESC_COL = "description"
        val TIME_COL = "time"
        val INGR_COL = "ingredients"
        val STEP_COL = "steps"
    }
}