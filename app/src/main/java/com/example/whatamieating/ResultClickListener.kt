package com.example.whatamieating

import com.example.whatamieating.model.remote.Result

interface ResultClickListener {
    fun onClick(result: Result)
}