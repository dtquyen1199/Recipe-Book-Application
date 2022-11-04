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

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
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
        //val insertQuery2 = ("INSERT INTO $TABLE_NAME ($NAME_COL, $DESC_COL) VALUES ('Pho','Hearty Recipe for a winter day');")
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




    // This method is for adding data in our database
    fun addRecipe(userInputRecipe : Recipe){

        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair


        //$ID_COL, $NAME_COL, $DESC_COL, $INGR_COL, $STEP_COL, $TIME_COL
        values.put(NAME_COL, userInputRecipe.name)
        values.put(DESC_COL, userInputRecipe.desc)

        //implement this
        values.put(INGR_COL, "")
        values.put(STEP_COL, "")
        values.put(TIME_COL, "")



        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        val result = db.insert(TABLE_NAME, null, values)


        if (result == -1.toLong()){
            //What do i pass for context here?
            //Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
        }else{

        }

        // at last we are
        // closing our database
        db.close()
    }

    // below method is to get
    // all data from our database
    fun getName(): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase
        // below code returns a cursor to
        // read data from the database



        return db.rawQuery("SELECT * FROM recipe", null)

    }


    //return all entries as a list
    fun readData(): MutableList<Recipe>{
        var list : MutableList<Recipe> = ArrayList()
        val db = this.readableDatabase

        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)
        //if cursors not null and has at least 1 val
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
        //values.put(NAME_COL, recipe.name)
        values.put(DESC_COL, recipe.desc)
        val db = this.writableDatabase
        db.update(TABLE_NAME, values, "$NAME_COL = ?", arrayOf(recipe.name))
    }

    fun delete(recipe : Recipe){

    }

    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "RECIPES_APP_DB"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "Recipe"

        // below is the variable for id column
        val ID_COL = "id"

        // below is the variable for name column
        val NAME_COL = "name"

        // below is the variable for age column
        val DESC_COL = "description"


        val TIME_COL = "time"

        val INGR_COL = "ingredients"

        val STEP_COL = "steps"
    }
}