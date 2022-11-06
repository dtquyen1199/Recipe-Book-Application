package com.example.customapp

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class ErrorHandlingDialog(context: Context): AlertDialog.Builder(context) {


        lateinit var onResponse: (r : ResponseType) -> Unit

        enum class ResponseType {
            YES, NO, CANCEL
        }
        fun show(title: String, message: String, listener: (r : ResponseType) -> Unit) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            onResponse = listener

            // performing positive action
            builder.setPositiveButton(R.string.continue_editing) { _, _ ->
                onResponse(ResponseType.YES)
                //Stay in editing
            }
            // performing negative action
            builder.setNegativeButton(R.string.discard_edit) { _, _ ->
                onResponse(ResponseType.NO)
                //Discard editing
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()

            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
        }

}