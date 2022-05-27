package com.aljawad.sons.marvel.dtos.models

import androidx.recyclerview.widget.DiffUtil
import com.aljawad.sons.marvel.dtos.responses.ListWrapperModel

data class ComicModel(
    var id: Int?,
    var digitalId: Int?,
    var title: String?,
    var issueNumber: Double?,
    var variantDescription: String?,
    var description: String?,
    var modified: String?,
    var isbn: String?,
    var upc: String?,
    var diamondCode: String?,
    var ean: String?,
    var issn: String?,

    var format: String?,
    var pageCount: Int?,
    var textObjects: TextObjectModel?,
    var resourceURI: String?,
    var urls: MutableList<UrlModel>?,

    var series: MutableList<CollectionSummaryModel>?,
    var variants: MutableList<CollectionSummaryModel>?,
    var collections: MutableList<CollectionSummaryModel>?,
    var collectedIssues: MutableList<CollectionSummaryModel>?,

    var dates: MutableList<ComicDateModel>?,
    var prices: MutableList<ComicPriceModel>?,
    var thumbnail: ImageModel?,
    var images: MutableList<ImageModel>?,

    var creators: MutableList<ListWrapperModel<CollectionSummaryModel>>?,


    var characters: MutableList<CollectionSummaryModel>?,
    var stories: MutableList<CollectionSummaryModel>?,
    var events: MutableList<CollectionSummaryModel>?
) {
    companion object {
        var diffUtil: DiffUtil.ItemCallback<ComicModel> =
            object : DiffUtil.ItemCallback<ComicModel>() {
                override fun areItemsTheSame(
                    oldItem: ComicModel,
                    newItem: ComicModel
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: ComicModel,
                    newItem: ComicModel
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }

}