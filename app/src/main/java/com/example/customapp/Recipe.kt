package com.example.customapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(var id: Int = -1, var name: String = "", var desc: String= "", var ingr: String = "", var step: String = "", var time: Int = 0) : Parcelable {


}

