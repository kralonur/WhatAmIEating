package com.example.whatamieating

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.whatamieating.databinding.ItemRecipeBinding
import com.example.whatamieating.model.remote.Result

class RecipeListViewHolder(private val binding: ItemRecipeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(result: Result) {
        binding.itemRecipeTitle.text = result.title
        Glide.with(binding.root.context)
            .load(result.image)
            .into(binding.itemRecipeImage)
    }
}