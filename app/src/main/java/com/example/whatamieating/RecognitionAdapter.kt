package com.example.whatamieating

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.whatamieating.databinding.ItemRecognitionBinding

class RecognitionAdapter(private val clickListener: RecognitionClickListener) :
    ListAdapter<Recognition, RecognitionViewHolder>(ListItemCallback()) {

    private class ListItemCallback : DiffUtil.ItemCallback<Recognition>() {
        override fun areItemsTheSame(oldItem: Recognition, newItem: Recognition): Boolean {
            return oldItem.label == newItem.label
        }

        override fun areContentsTheSame(oldItem: Recognition, newItem: Recognition): Boolean {
            return oldItem.confidence == newItem.confidence
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecognitionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecognitionBinding.inflate(inflater, parent, false)
        return RecognitionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecognitionViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}