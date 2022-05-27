package com.aljawad.sons.marvel.dtos.responses

data class ListWrapperModel<T>(
    var available: Int?,
    var returned: Int?,
    var collectionURI: String?,
    var items: MutableList<T>?,
) {
}