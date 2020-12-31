package com.example.whatamieating.ui.recipe_list.recview

import com.example.whatamieating.model.remote.Result

interface ResultClickListener {
    fun onClick(result: Result)
}