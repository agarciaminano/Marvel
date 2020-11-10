package com.marvel.feature_common.base.ui.toolbar

import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar

class ToolbarManager(private val builder: FragmentToolbar, private val container: View) {

    fun prepareToolbar(): Toolbar {
        return with(builder) {
            val fragmentToolbar = container.findViewById<Toolbar>(resId)

            menuRes?.let { menuRes ->
                fragmentToolbar.inflateMenu(menuRes)
            }

            title?.let { titleRes ->
                fragmentToolbar.setTitle(titleRes)
            }

            if (menuItems != null && menuItems.isNotEmpty() && menuClick != null) {
                val menu = fragmentToolbar.menu
                for (menuItemId in menuItems) {
                    (menu.findItem(menuItemId) as MenuItem).setOnMenuItemClickListener(
                        menuClick
                    )
                }
            }
            fragmentToolbar
        }

    }
}