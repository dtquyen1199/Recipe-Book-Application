package com.example.customapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
//$ID_COL, $NAME_COL, $DESC_COL, $INGR_COL, $STEP_COL, $TIME_COL
data class Recipe(var id: Int = 0, var name: String = "", var desc: String= "", var ingr: String = "", var step: String = "", var time: Int = 0) : Parcelable {


}

