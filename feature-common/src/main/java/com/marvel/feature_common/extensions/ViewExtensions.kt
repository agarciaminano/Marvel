package com.marvel.feature_common.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import com.google.android.material.appbar.AppBarLayout
import com.squareup.picasso.Picasso

fun ImageView.fromPicasso(url: String) {
    Picasso.get().load(url).into(this)
}

fun EditText.setOnTextChangedListener(block : (String) -> Unit) {
    this.addTextChangedListener(object  : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            block(p0.toString())
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    })
}

fun AppBarLayout.isExpanded() = height - bottom == 0