package com.example.whatamieating

import androidx.recyclerview.widget.RecyclerView
import com.example.whatamieating.databinding.ItemRecognitionBinding

class RecognitionViewHolder(private val binding: ItemRecognitionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(recognition: Recognition, clickListener: RecognitionClickListener) {
        binding.recognition = recognition
        binding.layout.setOnClickListener { clickListener.onClick(recognition) }
        binding.executePendingBindings()
    }
}