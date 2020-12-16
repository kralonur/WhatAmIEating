package com.example.whatamieating.ui.recipe_information.overview

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.whatamieating.databinding.FragmentOverviewBinding
import com.example.whatamieating.model.domain.RecipeInformation
import com.example.whatamieating.model.domain.ResultWrapper
import com.example.whatamieating.ui.recipe_information.RecipeInformationViewModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement

class OverviewFragment : Fragment() {
    private lateinit var binding: FragmentOverviewBinding
    private val viewModel by activityViewModels<RecipeInformationViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.recipeInfo.observe(viewLifecycleOwner) {
            when (it) {
                is ResultWrapper.Success -> onResultSuccess(it)
                else -> { //Errors handled on RecipeInformation fragment
                }
            }
        }
    }

    private fun onResultSuccess(
        it: ResultWrapper.Success<RecipeInformation>
    ) {

        val aaChartModel: AAChartModel = AAChartModel()
            .chartType(AAChartType.Pie)
            .title("Caloric Breakdown")
            .dataLabelsEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.fragmentOverviewDetails.text =
                Html.fromHtml(it.value.summary, Html.FROM_HTML_MODE_COMPACT)
        } else {
            binding.fragmentOverviewDetails.text =
                Html.fromHtml(it.value.summary)
        }

        aaChartModel.series(
            arrayOf(
                AASeriesElement()
                    .name("Percentage %:")
                    .data(
                        arrayOf(
                            arrayOf("fat", it.value.caloricBreakdown.percentFat),
                            arrayOf(
                                "protein",
                                it.value.caloricBreakdown.percentProtein
                            ),
                            arrayOf("carbs", it.value.caloricBreakdown.percentCarbs)
                        )
                    )
            )
        )

        binding.aaChartView.aa_drawChartWithChartModel(aaChartModel)
    }
}