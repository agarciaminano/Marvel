package com.marvel.feature_common.base.ui.toolbar

import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import com.marvel.feature_common.R

class FragmentToolbar(
    @IdRes val resId: Int,
    @StringRes val title: Int?,
    @MenuRes val menuRes: Int?,
    val menuItems: List<Int>?,
    val menuClick: MenuItem.OnMenuItemClickListener?
) {

    class Builder {
        @IdRes
        private var resId: Int = R.id.main_toolbar

        @StringRes
        private var title: Int? = null

        @MenuRes
        private var menuRes: Int? = null
        private var menuItems: List<Int>? = null
        private var menuClicks: MenuItem.OnMenuItemClickListener? = null

        fun withResId(id: Int) = apply {
            resId = id
        }

        fun withTitle(@StringRes titleRes: Int) = apply {
            title = titleRes
        }


        fun withMenu(
            @MenuRes menuRes: Int,
            menuItems: List<Int>,
            menuClicks: MenuItem.OnMenuItemClickListener?
        ) = apply {
            this.menuRes = menuRes
            this.menuItems = menuItems
            this.menuClicks = menuClicks
        }

        fun build(): FragmentToolbar {
            return FragmentToolbar(resId, title, menuRes, menuItems, menuClicks)
        }
    }


}