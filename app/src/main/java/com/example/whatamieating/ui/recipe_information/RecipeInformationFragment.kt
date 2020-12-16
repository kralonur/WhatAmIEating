package com.example.whatamieating.ui.recipe_information

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.whatamieating.R
import com.example.whatamieating.databinding.FragmentRecipeInformationBinding
import com.example.whatamieating.model.domain.ResultWrapper
import com.example.whatamieating.ui.recipe_information.ingredients.IngredientsFragment
import com.example.whatamieating.ui.recipe_information.nutrients.NutrientsFragment
import com.example.whatamieating.ui.recipe_information.overview.OverviewFragment
import com.example.whatamieating.ui.recipe_information.steps.StepsFragment
import com.example.whatamieating.util.showShortText
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber

class RecipeInformationFragment : Fragment() {
    private val viewModel by activityViewModels<RecipeInformationViewModel>()
    private lateinit var binding: FragmentRecipeInformationBinding
    private val args by navArgs<RecipeInformationFragmentArgs>()


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

        val recipeId = args.recipeId

        viewModel.updateRecipeId(recipeId)

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
            offscreenPageLimit = 1
        }

        (binding.viewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles.getOrNull(position)
        }.attach()

        binding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val foundView = childFragmentManager.findFragmentByTag("f$position")?.view
                adjustViewPagerFragmentHeight(foundView)
            }
        })
    }

    private fun adjustViewPagerFragmentHeight(view: View?) {
        view?.let {
            it.post {
                val outMetrics = DisplayMetrics()

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                    val display = requireActivity().display
                    display?.getRealMetrics(outMetrics)
                } else {
                    @Suppress("DEPRECATION")
                    val display = requireActivity().windowManager.defaultDisplay
                    @Suppress("DEPRECATION")
                    display.getMetrics(outMetrics)
                }
                val desiredMinHeight = outMetrics.heightPixels - dpToPx(requireContext(), 256)

                val wMeasureSpec =
                    View.MeasureSpec.makeMeasureSpec(it.width, View.MeasureSpec.EXACTLY)
                val hMeasureSpec =
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                it.measure(wMeasureSpec, hMeasureSpec)

                val desiredHeight = if (desiredMinHeight < it.measuredHeight) {
                    it.measuredHeight
                } else {
                    desiredMinHeight
                }

                binding.viewPager.layoutParams =
                    (binding.viewPager.layoutParams as ConstraintLayout.LayoutParams)
                        .also { layoutParams -> layoutParams.height = desiredHeight }

                it.layoutParams = (it.layoutParams as FrameLayout.LayoutParams)
                    .also { layoutParams -> layoutParams.height = desiredHeight }
            }
        }
    }


    private fun dpToPx(context: Context, dp: Int): Int {
        return (dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

}