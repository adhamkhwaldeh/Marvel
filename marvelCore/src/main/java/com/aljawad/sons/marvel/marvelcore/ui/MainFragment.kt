package com.aljawad.sons.marvel.marvelcore.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.aljawad.sons.mainlibrary.enums.LayoutStatesEnum
import com.aljawad.sons.mainlibrary.extensions.launchOnLifecycleScope
import com.aljawad.sons.mainlibrary.extensions.observe
import com.aljawad.sons.mainlibrary.interfaces.OnRefreshLayoutListener
import com.aljawad.sons.mainlibrary.states.BaseState
import com.aljawad.sons.mainlibrary.states.PoJoByIdStateLoadedSuccessfully
import com.aljawad.sons.marvel.marvelcore.R
import com.aljawad.sons.marvel.marvelcore.adapters.CharacterPagingAdapter
import com.aljawad.sons.marvel.marvelcore.adapters.ComicsAdapter
import com.aljawad.sons.marvel.marvelcore.databinding.FragmentMainBinding
import com.aljawad.sons.marvel.marvelcore.viewModels.CharacterViewModel
import com.aljawad.sons.marvel.marvelrepository.paging.adapters.PagingLoadStateAdapter
import com.aljawad.sons.marvel.marvelrepository.paging.adapters.PagingLoadStateHorizontalAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: CharacterViewModel by activityViewModels()

    private val characterAdapter: CharacterPagingAdapter by lazy {
        CharacterPagingAdapter(
            onCharacterClick = {
                viewModel.loadCharacterComics(it.id) {
                    collectCharacterComics()
                }
            }
        )
    }

    private val comicsAdapter: ComicsAdapter by lazy {
        ComicsAdapter(
//            onCharacterClick = {
//                viewModel.loadCharacterComics(it.id)
//                collectCharacterComics()

//                MaterialAlertDialogBuilder(requireContext())
//                    .setCancelable(false)
//                    .setTitle(getString(R.string.delete))
//                    .setMessage(R.string.doYouWantToDeleteThisUser)
//                    .setNegativeButton(getString(R.string.cancel)) { dialog, which ->
//                        // Respond to negative button press
//                    }
//                    .setPositiveButton(getString(R.string.delete)) { dialog, which ->
//                        // Respond to positive button press
//                        userViewModel.deleteUser(it.id!!)
//                        onUserDelete()
//                    }
//                    .show()
//            }
        )
    }


    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.comicsRecyclerView.adapter = comicsAdapter

        viewModel.getCharacterList()

        bindVM()

        binding.comicsStatesLayout.setOnRefreshLayoutListener(
            object : OnRefreshLayoutListener {
                override fun onRefresh() {
                    viewModel.selectedCharacterId?.let {
                        viewModel.loadCharacterComics(it) {
                            collectCharacterComics()
                        }
                    }
                }

                override fun onRequestPermission() {

                }

                override fun onRefreshFromInternalServerError() {

                }
            },
        )

    }


    private fun bindVM() = with(binding) {
        with(characterAdapter) {
            swipeRefresh.setOnRefreshListener { characterAdapter.refresh() }
            characterRecyclerView.adapter = withLoadStateHeaderAndFooter(
                header = PagingLoadStateHorizontalAdapter(
                    this
                ),
                footer = PagingLoadStateHorizontalAdapter(
                    this
                )
            )
            with(viewModel) {
                launchOnLifecycleScope {
                    usersFlow.collectLatest {
                        submitData(it)
                    }
                }
                launchOnLifecycleScope {
                    loadStateFlow.collectLatest {
                        swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                    }
                }
                launchOnLifecycleScope {
                    loadStateFlow.distinctUntilChangedBy { it.refresh }
                        .filter { it.refresh is LoadState.NotLoading }
                        .collect {
                            characterRecyclerView.scrollToPosition(0)
//                                binding.statesLayout.FlipLayout(if (attendanceAdapter.itemCount == 0)
//                                    LayoutStatesEnum.Nodatalayout else LayoutStatesEnum.SuccessLayout)
//                                if (it.append is LoadState.Error) {
//
//                                }
                        }

                    loadStateFlow.distinctUntilChangedBy { it.refresh }.collect {

                    }
                }
            }
        }
    }

    private fun collectCharacterComics() {
        lifecycleScope.launchWhenResumed {
            viewModel.characterComicsFlow.collect {

                Log.v("", "")
                when (it) {
                    is BaseState.Loading -> {
                        binding.comicsStatesLayout.FlipLayout(LayoutStatesEnum.Waitinglayout)
//                        progressDialog.showDialog(childFragmentManager)
                    }
                    is BaseState.LoadingDismiss -> {
//                        ProgressDialog.closeDialog(childFragmentManager)
                    }
                    is BaseState.InternalServerError -> {
                        binding.comicsStatesLayout.FlipLayout(LayoutStatesEnum.INTERNALSERVERERROR)
//                        Snackbar.make(
//                            binding.root,
//                            it.message ?: getText(R.string.unexpectedErrorHappened),
//                            Snackbar.LENGTH_LONG
//                        ).show()
                    }
                    is BaseState.NoAuthorized -> {
                        binding.comicsStatesLayout.FlipLayout(LayoutStatesEnum.NopermissionLayout)
//                        Snackbar.make(
//                            binding.root,
//                            getText(R.string.unAuthorized),
//                            Snackbar.LENGTH_LONG
//                        ).show()
                    }
                    is BaseState.NoInternetError -> {
                        binding.comicsStatesLayout.FlipLayout(LayoutStatesEnum.Noconnectionlayout)
//                        Snackbar.make(
//                            binding.root,
//                            getText(R.string.checkYourInternetConnection),
//                            Snackbar.LENGTH_LONG
//                        ).show()
                    }
                    is BaseState.Conflict -> {
//                        Snackbar.make(
//                            binding.root,
//                            it.message ?: getText(R.string.dataIssue),
//                            Snackbar.LENGTH_LONG
//                        ).show()
                    }
                    is BaseState.NotDataFound -> {
                        binding.comicsStatesLayout.FlipLayout(LayoutStatesEnum.Nodatalayout)
                    }
                    is BaseState.FeaturedState -> {
                        if (it is PoJoByIdStateLoadedSuccessfully) {

                            val result = it.data?.data?.results
                            comicsAdapter.submitList(result)
                            if (result.isNullOrEmpty()) {
                                binding.comicsStatesLayout.FlipLayout(LayoutStatesEnum.Nodatalayout)
                            } else {
                                binding.comicsStatesLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
                            }
                        }
//                        Snackbar.make(
//                            binding.root,
//                            getText(R.string.userHasBeenDeletedSuccessfully),
//                            Snackbar.LENGTH_LONG
//                        ).show()
//                        userAdapter.refresh()
                    }
                }

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}