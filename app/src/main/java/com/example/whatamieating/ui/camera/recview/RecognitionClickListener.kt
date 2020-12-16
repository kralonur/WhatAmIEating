package com.example.whatamieating.ui.camera.recview

import com.example.whatamieating.model.domain.Recognition

interface RecognitionClickListener {
    fun onClick(recognition: Recognition)
}