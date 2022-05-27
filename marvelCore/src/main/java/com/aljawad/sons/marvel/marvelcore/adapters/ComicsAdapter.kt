package com.aljawad.sons.marvel.marvelcore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aljawad.sons.marvel.dtos.models.CharacterModel
import com.aljawad.sons.marvel.dtos.models.ComicModel
import com.aljawad.sons.marvel.marvelcore.databinding.ComicsListItemBinding

class ComicsAdapter : ListAdapter<ComicModel, ComicsAdapter.ComicsViewHolder>(ComicModel.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        return ComicsViewHolder(
            ComicsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        getItem(position)?.let {
            holder(it)
        }
    }

    inner class ComicsViewHolder(private val binding: ComicsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        operator fun invoke(item: ComicModel) = with(binding) {
            this.item = item
        }
    }

}