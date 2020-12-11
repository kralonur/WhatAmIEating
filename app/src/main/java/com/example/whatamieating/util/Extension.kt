package com.example.whatamieating.util

import android.content.Context
import android.widget.Toast

fun Context.showShortText(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}
