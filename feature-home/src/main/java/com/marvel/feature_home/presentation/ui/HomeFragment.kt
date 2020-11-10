package com.marvel.feature_home.presentation.ui

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.marvel.feature_common.base.Result
import com.marvel.feature_common.base.ui.BaseFragment
import com.marvel.feature_common.base.ui.toolbar.FragmentToolbar
import com.marvel.feature_common.extensions.isExpanded
import com.marvel.feature_common.extensions.setOnTextChangedListener
import com.marvel.feature_home.R
import com.marvel.feature_home.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*


@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()
    private val recyclerAdapter = CharacterRecyclerAdapter(mutableListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        home_fragment_character_recycler.apply {
            adapter = recyclerAdapter
            layoutManager =
                GridLayoutManager(requireContext(), GRID_COLUMS, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            setupScrollListener(this, layoutManager as GridLayoutManager)
        }
        disableToolbarScroll()
        initObserve()
    }

    private fun initObserve() {
        homeViewModel.charactersLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> showProgressBar()
                is Result.Success -> {
                    recyclerAdapter.addItems(it.data)
                    hideProgressBar()
                }
                is Result.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "ERROR!",
                        Toast.LENGTH_SHORT
                    ).show()
                    hideProgressBar()
                }
            }
        }
        homeViewModel.filteredCharacters.observe(viewLifecycleOwner) {
            recyclerAdapter.clearItems()
            if (it.isNotEmpty()) {
                recyclerAdapter.addItems(it)
            }
        }
    }

    override fun buildToolbar(): FragmentToolbar {
        return FragmentToolbar.Builder()
            .withResId(R.id.toolbar_home)
            .withMenu(R.menu.toolbar_menu, listOf(R.id.home_toolbar_ic_search)) {
                when (it.itemId) {
                    R.id.home_toolbar_ic_search -> {
                        onSearchToolbarClick()
                        true
                    }
                    else -> {
                        true
                    }
                }
            }
            .build()
    }

    private fun disableToolbarScroll() {
        val mainAppBarLayout = main_appbar_layout
        val params = mainAppBarLayout.layoutParams as CoordinatorLayout.LayoutParams
        if (params.behavior == null)
            params.behavior = AppBarLayout.Behavior()
        val behaviour = params.behavior as AppBarLayout.Behavior
        behaviour.setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
            override fun canDrag(appBarLayout: AppBarLayout): Boolean {
                return false
            }
        })
    }

    private fun initListeners() {
        home_toolbar_cancel_tv.setOnClickListener {
            main_appbar_layout.setExpanded(false)
            home_toolbar_search_et.setText("")
        }
        home_toolbar_search_et.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN &&
                keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                main_appbar_layout.setExpanded(false)
                true
            } else {
                false
            }
        }
        home_toolbar_search_et.setOnTextChangedListener {
            homeViewModel.onQueryTextChanged(it, recyclerAdapter.getItems())
        }
    }

    private fun onSearchToolbarClick() {
        main_appbar_layout.setExpanded(!main_appbar_layout.isExpanded())
    }

    private fun setupScrollListener(recyclerView: RecyclerView, layoutManager: GridLayoutManager) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount: Int = layoutManager.childCount
                val totalItemCount: Int = layoutManager.itemCount
                val firstVisibleItemPosition: Int =
                    layoutManager.findFirstVisibleItemPosition()
                homeViewModel.listScrolled(
                    totalItemCount = totalItemCount,
                    visibleItemCount = visibleItemCount,
                    firstVisibleItemPosition = firstVisibleItemPosition
                )
            }
        })
    }

    companion object {
        const val GRID_COLUMS = 2
        fun newInstance(): Fragment {
            return HomeFragment()
        }
    }
}