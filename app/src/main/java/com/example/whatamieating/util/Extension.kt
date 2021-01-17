package com.example.whatamieating.util

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Context.showShortText(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.showAlertDialog(view: View): AlertDialog {
    view.removeParent()
    return MaterialAlertDialogBuilder(this)
        .setView(view)
        .show()
}

//Removes view's parent if it already has one
private fun View.removeParent() {
    if (this.parent != null) (this.parent as ViewGroup).removeView(this)
}
