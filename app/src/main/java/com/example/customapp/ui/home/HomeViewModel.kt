package com.example.customapp.ui.home

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.customapp.DBHelper
import com.example.customapp.MainActivity
import com.example.customapp.Recipe
import com.google.android.material.internal.ContextUtils.getActivity

//ViewModel() ,
//class HomeViewModel: ViewModel() {
class HomeViewModel(application: Application) : AndroidViewModel(application) {





    val db = DBHelper(application, null)
    //if run withhout this code then delete
    /*private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    val text: LiveData<String> = _text

     */

    private val _name = MutableLiveData<String>().apply {
            //value = "Name here"
        value = db.readData()[0].name
    }
    val name: LiveData<String> = _name

    private val _desc = MutableLiveData<String>().apply {
        //value = "Desc here"
        value = db.readData()[0].desc
    }
    val desc: LiveData<String> = _desc

    private var _recipes = MutableLiveData<List<Recipe>>().apply{
        value = db.readData()
    }

    var liveDataRecipes: MutableLiveData<List<Recipe>>
        get() {return _recipes}
        set(value) {_recipes = value}



}