package com.example.whatamieating.ui.recipe_information.nutrients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.whatamieating.databinding.FragmentNutrientsBinding
import com.example.whatamieating.model.domain.RecipeInformation
import com.example.whatamieating.model.domain.ResultWrapper
import com.example.whatamieating.ui.recipe_information.RecipeInformationViewModel
import com.example.whatamieating.ui.recipe_information.recview.StringAdapter

class NutrientsFragment : Fragment() {
    private lateinit var binding: FragmentNutrientsBinding
    private val viewModel by activityViewModels<RecipeInformationViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNutrientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StringAdapter()

        binding.recView.adapter = adapter

        viewModel.recipeInfo.observe(viewLifecycleOwner) {
            when (it) {
                is ResultWrapper.Success -> onResultSuccess(it, adapter)
                else -> { //Errors handled on RecipeInformation fragment
                }
            }
        }
    }

    private fun onResultSuccess(
        it: ResultWrapper.Success<RecipeInformation>,
        adapter: StringAdapter
    ) {
        val list = mutableListOf("<h2><strong>Properties:</strong></h2>")
        list += it.value.properties.map { property -> "${property.title}: ${property.amount} ${property.unit}" }
        list += listOf("", "<h2><strong>Nutrients:</strong></h2>")
        list += it.value.nutrients.map { nutrient -> "${nutrient.title}: ${nutrient.amount} ${nutrient.unit}" }
        list += listOf("", "<h2><strong>Flavanoids:</strong></h2>")
        list += it.value.flavanoids.map { flavanoid -> "${flavanoid.title}: ${flavanoid.amount} ${flavanoid.unit}" }

        adapter.submitList(list)
    }
}