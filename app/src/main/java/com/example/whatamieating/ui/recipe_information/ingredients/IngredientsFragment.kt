package com.example.whatamieating.ui.recipe_information.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.whatamieating.databinding.FragmentIngredientsBinding
import com.example.whatamieating.model.domain.ResultWrapper
import com.example.whatamieating.ui.recipe_information.RecipeInformationViewModel
import com.example.whatamieating.ui.recipe_information.recview.StringAdapter
import com.example.whatamieating.util.showShortText
import timber.log.Timber

class IngredientsFragment : Fragment() {
    private lateinit var binding: FragmentIngredientsBinding
    private val viewModel by activityViewModels<RecipeInformationViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StringAdapter()

        binding.recView.adapter = adapter

        viewModel.recipeInfo.observe(viewLifecycleOwner) {
            when (it) {
                ResultWrapper.Loading -> Timber.i("loading")
                is ResultWrapper.Success -> {
                    adapter.submitList(it.value.ingredients.map { ingredient -> ingredient.desc })
                }
                ResultWrapper.Error -> requireContext().showShortText("unknown error")
                ResultWrapper.NetworkError -> requireContext().showShortText("no internet")
                is ResultWrapper.ServerError -> requireContext().showShortText("error code is: ${it.code}")
            }
        }
    }
}