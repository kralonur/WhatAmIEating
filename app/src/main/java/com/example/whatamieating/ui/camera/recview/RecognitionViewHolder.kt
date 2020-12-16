package com.example.whatamieating.ui.camera.recview

import androidx.recyclerview.widget.RecyclerView
import com.example.whatamieating.databinding.ItemRecognitionBinding
import com.example.whatamieating.model.domain.Recognition

class RecognitionViewHolder(private val binding: ItemRecognitionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(recognition: Recognition, clickListener: RecognitionClickListener) {
        binding.recognition = recognition
        binding.layout.setOnClickListener { clickListener.onClick(recognition) }
        binding.executePendingBindings()
    }
}