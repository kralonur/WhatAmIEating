package com.example.whatamieating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.whatamieating.databinding.FragmentRecipeListBinding
import com.example.whatamieating.model.domain.ResultWrapper
import com.example.whatamieating.util.showShortText

class RecipeListFragment : Fragment() {
    private val viewModel by viewModels<RecipeListViewModel>()
    private lateinit var binding: FragmentRecipeListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecipeListAdapter()

        binding.recView.adapter = adapter

        viewModel.searchRecipe("kebap").observe(viewLifecycleOwner) {
            when (it) {
                ResultWrapper.Loading -> requireContext().showShortText("Loading")
                is ResultWrapper.Success -> {
                    requireContext().showShortText("${it.value.totalResults} result found")
                    adapter.submitList(it.value.results)
                }
                ResultWrapper.Error -> requireContext().showShortText("Error")
                ResultWrapper.NetworkError -> requireContext().showShortText("Network Error: Check your internet connection")
                is ResultWrapper.ServerError -> requireContext().showShortText("Server Error: ${it.code}")
            }
        }
    }
}