package com.example.whatamieating.util

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.showShortText(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.showAlertDialog(view: View): AlertDialog {
    return MaterialAlertDialogBuilder(this)
        .setView(view)
        .show()
}
