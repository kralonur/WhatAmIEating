package com.example.whatamieating.ui.recipe_information.recview

import android.os.Build
import android.text.Html
import androidx.recyclerview.widget.RecyclerView
import com.example.whatamieating.databinding.ItemTextviewBinding

class StringViewHolder(private val binding: ItemTextviewBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(string: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.text.text =
                Html.fromHtml(string, Html.FROM_HTML_MODE_COMPACT)
        } else {
            binding.text.text = Html.fromHtml(string)
        }
    }
}