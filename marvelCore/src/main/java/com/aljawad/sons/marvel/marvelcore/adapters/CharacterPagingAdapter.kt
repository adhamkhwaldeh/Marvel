package com.aljawad.sons.marvel.marvelcore.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aljawad.sons.marvel.dtos.models.CharacterModel
import com.aljawad.sons.marvel.marvelcore.databinding.CharacterListItemBinding

class CharacterPagingAdapter(private val onCharacterClick: (CharacterModel) -> Unit) :
    PagingDataAdapter<CharacterModel, CharacterPagingAdapter.CharacterViewHolder>(CharacterModel.diffUtil) {

    companion object {
        private const val TYPE_USER = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView =
            CharacterListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val layoutParams = itemView.root.layoutParams
        layoutParams.width = (parent.width * 0.3).toInt()
        itemView.root.layoutParams = layoutParams
        return CharacterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder(it) }
        holder.itemView.setOnClickListener {
            item?.let { onCharacterClick(it) }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_USER
    }

    inner class CharacterViewHolder(private val binding: CharacterListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        operator fun invoke(item: CharacterModel) = with(binding) {
            this.item = item
        }
    }

//    inner class LocationSeparatorViewHolder(private val binding: ItemSeparatorLocationBinding) :
//            RecyclerView.ViewHolder(binding.root)

//    object LocationComparator : DiffUtil.ItemCallback<LocationModel>() {
//        override fun areItemsTheSame(oldItem: LocationModel, newItem: LocationModel): Boolean {
//            val isSameLocationData = oldItem is LocationModel.LocationData
//                    && newItem is LocationModel.LocationData
//                    && oldItem.id == newItem.id
//
//            val isSameSeparator = oldItem is LocationModel.LocationSeparator
//                    && newItem is LocationModel.LocationSeparator
//                    && oldItem.tag == newItem.tag
//
//            return isSameLocationData || isSameSeparator
//        }
//
//        override fun areContentsTheSame(oldItem: LocationModel, newItem: LocationModel) =
//                oldItem == newItem
//    }


}