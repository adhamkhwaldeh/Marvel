package com.aljawad.sons.marvel.dtos.models

import androidx.databinding.BaseObservable
import androidx.recyclerview.widget.DiffUtil
import com.aljawad.sons.marvel.dtos.responses.*

data class CharacterModel(
    var id: Int,
    var name: String?,
    var description: String?,
    var modified: String?,
    var resourceURI: String?,
    var urls: MutableList<UrlModel>?,
    var thumbnail: ImageModel?,
    var comics: ListWrapperModel<CollectionSummaryModel>?,

    var stories: ListWrapperModel<CollectionSummaryModel>?,
    var events: ListWrapperModel<CollectionSummaryModel>?,
    var series: ListWrapperModel<CollectionSummaryModel>?,
) : BaseObservable() {

    companion object {
        var diffUtil: DiffUtil.ItemCallback<CharacterModel> =
            object : DiffUtil.ItemCallback<CharacterModel>() {
                override fun areItemsTheSame(
                    oldItem: CharacterModel,
                    newItem: CharacterModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: CharacterModel,
                    newItem: CharacterModel
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

//    @Bindable
//    fun isMale() = gender == GenderEnum.MALE.gender
//
//    @Bindable
//    fun isActive() = status == StatusEnum.ACTIVE.status

}
