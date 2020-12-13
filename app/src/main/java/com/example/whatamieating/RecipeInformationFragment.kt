package com.example.whatamieating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.whatamieating.databinding.FragmentRecipeInformationBinding
import com.example.whatamieating.model.domain.ResultWrapper
import com.example.whatamieating.util.showShortText
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber

class RecipeInformationFragment : Fragment() {
    private val viewModel by activityViewModels<RecipeInformationViewModel>()
    private lateinit var binding: FragmentRecipeInformationBinding

    private val fragments by lazy {
        listOf(
            OverviewFragment(),
            IngredientsFragment(),
            StepsFragment(),
            NutrientsFragment()
        )
    }

    private val titles by lazy {
        listOf(
            getString(R.string.overview),
            getString(R.string.ingredients),
            getString(R.string.steps),
            getString(R.string.nutrients)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.updateRecipeId(638218)

        viewModel.recipeInfo.observe(viewLifecycleOwner) {
            when (it) {
                ResultWrapper.Loading -> Timber.i("loading")
                is ResultWrapper.Success -> {
                    binding.fragmentInfoFoodTitle.text = it.value.dishName
                    binding.fragmentInfoFoodTime.text = "${it.value.cookingTime} minutes"
                    binding.fragmentInfoFoodLike.text = "${it.value.likesCount} likes"
                    binding.fragmentInfoCredit.text = "written by ${it.value.creditText}"
                    Glide.with(requireContext())
                        .load(it.value.dishImageUrl)
                        .into(binding.fragmentInfoImage)
                }
                ResultWrapper.Error -> requireContext().showShortText("unknown error")
                ResultWrapper.NetworkError -> requireContext().showShortText("no internet")
                is ResultWrapper.ServerError -> requireContext().showShortText("error code is: ${it.code}")
            }
        }

        val adapter = FragmentsPagerAdapter(
            fragments,
            childFragmentManager,
            lifecycle
        )

        binding.viewPager.apply {
            this.adapter = adapter
            offscreenPageLimit = fragments.size
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles.getOrNull(position)
        }.attach()
    }

}