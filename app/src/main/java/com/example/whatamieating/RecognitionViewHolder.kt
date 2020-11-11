package com.example.whatamieating

import androidx.recyclerview.widget.RecyclerView
import com.example.whatamieating.databinding.ItemRecognitionBinding

class RecognitionViewHolder(private val binding: ItemRecognitionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(recognition: Recognition) {
        binding.recognition = recognition
        binding.executePendingBindings()
    }
}