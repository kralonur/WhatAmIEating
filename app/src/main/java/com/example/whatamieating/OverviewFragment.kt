package com.example.whatamieating

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.whatamieating.databinding.FragmentOverviewBinding
import com.example.whatamieating.model.domain.ResultWrapper
import com.example.whatamieating.util.showShortText
import timber.log.Timber

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
                ResultWrapper.Loading -> Timber.i("loading")
                is ResultWrapper.Success -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        binding.textView8.text =
                            Html.fromHtml(it.value.summary, Html.FROM_HTML_MODE_COMPACT)
                    } else {
                        binding.textView8.text = Html.fromHtml(it.value.summary)
                    }
                }
                ResultWrapper.Error -> requireContext().showShortText("unknown error")
                ResultWrapper.NetworkError -> requireContext().showShortText("no internet")
                is ResultWrapper.ServerError -> requireContext().showShortText("error code is: ${it.code}")
            }
        }
    }
}