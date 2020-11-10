package com.marvel.feature_home.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marvel.feature_common.extensions.fromPicasso
import com.marvel.feature_home.R
import com.marvel.feature_home.presentation.model.CharacterViewData
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterRecyclerAdapter(private val characterList: MutableList<CharacterViewData>) :
    RecyclerView.Adapter<CharacterRecyclerAdapter.CharacterViewHolder>() {

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        )
    }

    fun addItems(characterItems: List<CharacterViewData>) {
        characterList.addAll(characterItems)
        notifyDataSetChanged()
    }

    fun clearItems() {
        characterList.clear()
        notifyDataSetChanged()
    }

    fun getItems() = characterList

    class CharacterViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(character: CharacterViewData) {
            view.item_character_name_tv.text = character.name
            with(character.thumbnail) {
                view.item_character_iv.fromPicasso("$path.$extension")
            }
        }
    }
}