package com.example.whatamieating.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.whatamieating.R
import com.example.whatamieating.databinding.DialogLottieBinding
import com.example.whatamieating.databinding.FragmentRecipeListBinding
import com.example.whatamieating.model.domain.ResultWrapper
import com.example.whatamieating.model.remote.Result
import com.example.whatamieating.model.remote.SearchRecipesResponse
import com.example.whatamieating.ui.recipe_list.recview.RecipeListAdapter
import com.example.whatamieating.ui.recipe_list.recview.ResultClickListener
import com.example.whatamieating.util.showAlertDialog
import com.example.whatamieating.util.showShortText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class RecipeListFragment : Fragment(), ResultClickListener {
    private val viewModel: RecipeListViewModel by viewModels()
    private lateinit var binding: FragmentRecipeListBinding
    private val args by navArgs<RecipeListFragmentArgs>()

    private lateinit var lottieBinding: DialogLottieBinding
    private lateinit var lottieDialog: AlertDialog

    private lateinit var query: String


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

        query = if (!viewModel.recipeQuery.value.isNullOrEmpty()) viewModel.recipeQuery.value
            ?: args.query
        else args.query

        val adapter = RecipeListAdapter(this)

        binding.recView.adapter = adapter

        binding.topAppBar.menu.apply {
            val searchItem = this.findItem(R.id.search)
            val searchView = searchItem?.actionView as SearchView
            searchView.queryHint = getString(R.string.search)
            searchView.setIconifiedByDefault(false)
            searchView.setQuery(query, true)
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        viewModel.setRecipeQuery(it)
                        searchView.clearFocus()
                        searchView.onActionViewCollapsed()
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?) = true
            })
        }

        lottieBinding = DialogLottieBinding.inflate(layoutInflater)

        viewModel.setRecipeQuery(query)

        viewModel.recipe.observe(viewLifecycleOwner) {
            loadingDialog(it)
            when (it) {
                ResultWrapper.Loading -> Timber.i(getString(R.string.loading))
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


    private fun <T> loadingDialog(result: ResultWrapper<T>) {
        if (result == ResultWrapper.Loading) {
            lottieDialog = requireContext().showAlertDialog(lottieBinding.root)

            lottieBinding.animationView.setAnimation(R.raw.recipe_list_lottie)
            lottieBinding.animationView.playAnimation()
        } else {
            GlobalScope.launch {
                delay(1000)
                lottieDialog.dismiss()
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