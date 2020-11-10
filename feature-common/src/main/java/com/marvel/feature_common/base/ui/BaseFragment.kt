package com.marvel.feature_common.base.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.marvel.feature_common.R
import com.marvel.feature_common.base.ui.toolbar.FragmentToolbar
import com.marvel.feature_common.base.ui.toolbar.ToolbarManager

open class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    protected var toolBar: Toolbar? = null
    private var progressBar: ProgressBar? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolBar = ToolbarManager(buildToolbar(), view)
            .prepareToolbar()
        progressBar = activity?.findViewById(R.id.main_progress_bar)

    }

    open fun buildToolbar(): FragmentToolbar {
        return FragmentToolbar.Builder()
            .withResId(R.id.main_toolbar)
            .withTitle(R.string.app_name)
            .build()
    }

    protected fun hideProgressBar() {
        progressBar?.visibility = View.GONE
    }

    protected fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }
}