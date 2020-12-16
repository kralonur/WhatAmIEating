package com.example.whatamieating.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.whatamieating.R
import com.example.whatamieating.databinding.FragmentRecipeListBinding
import com.example.whatamieating.model.domain.ResultWrapper
import com.example.whatamieating.model.remote.Result
import com.example.whatamieating.model.remote.SearchRecipesResponse
import com.example.whatamieating.ui.recipe_list.recview.RecipeListAdapter
import com.example.whatamieating.ui.recipe_list.recview.ResultClickListener
import com.example.whatamieating.util.showShortText

class RecipeListFragment : Fragment(), ResultClickListener {
    private val viewModel by viewModels<RecipeListViewModel>()
    private lateinit var binding: FragmentRecipeListBinding
    private val args by navArgs<RecipeListFragmentArgs>()


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

        val query = args.query

        val adapter = RecipeListAdapter(this)

        binding.recView.adapter = adapter

        viewModel.searchRecipe(query).observe(viewLifecycleOwner) {
            when (it) {
                ResultWrapper.Loading -> requireContext().showShortText(getString(R.string.loading))
                is ResultWrapper.Success -> onResultSuccess(it, adapter)
                ResultWrapper.Error -> requireContext().showShortText(getString(R.string.error))
                ResultWrapper.NetworkError -> requireContext().showShortText(getString(R.string.network_error))
                is ResultWrapper.ServerError -> requireContext().showShortText(
                    getString(
                        R.string.server_error,
                        it.code
                    )
                )
            }
        }
    }

    private fun onResultSuccess(
        it: ResultWrapper.Success<SearchRecipesResponse>,
        adapter: RecipeListAdapter
    ) {
        requireContext().showShortText(
            getString(
                R.string.results_found,
                it.value.totalResults
            )
        )
        adapter.submitList(it.value.results)
    }

    override fun onClick(result: Result) {
        findNavController().navigate(
            RecipeListFragmentDirections.actionRecipeListFragmentToRecipeInformationFragment(result.id)
        )
    }
}