package com.example.whatamieating

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraViewModel : ViewModel() {

    private val _recognitionList = MutableLiveData<List<Recognition>>()
    val recognitionList: LiveData<List<Recognition>>
        get() = _recognitionList

    fun updateData(recognitions: List<Recognition>) {
        _recognitionList.postValue(recognitions)
    }
}