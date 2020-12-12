package com.example.whatamieating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.whatamieating.databinding.FragmentNutrientsBinding
import com.example.whatamieating.model.domain.ResultWrapper
import com.example.whatamieating.util.showShortText
import timber.log.Timber

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
                ResultWrapper.Loading -> Timber.i("loading")
                is ResultWrapper.Success -> {
                    val list = mutableListOf("<h2><strong>Properties:</strong></h2>")
                    list += it.value.properties.map { property -> "${property.title}: ${property.amount} ${property.unit}" }
                    list += listOf("","<h2><strong>Nutrients:</strong></h2>")
                    list += it.value.nutrients.map { nutrient -> "${nutrient.title}: ${nutrient.amount} ${nutrient.unit}" }
                    list += listOf("", "<h2><strong>Flavanoids:</strong></h2>")
                    list += it.value.flavanoids.map { flavanoid -> "${flavanoid.title}: ${flavanoid.amount} ${flavanoid.unit}" }

                    adapter.submitList(list)
                }
                ResultWrapper.Error -> requireContext().showShortText("unknown error")
                ResultWrapper.NetworkError -> requireContext().showShortText("no internet")
                is ResultWrapper.ServerError -> requireContext().showShortText("error code is: ${it.code}")
            }
        }
    }
}