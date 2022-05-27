package com.aljawad.sons.marvel.dtos.models

data class ImageModel(
    var path: String?,
    var extension: String?,
) {

    fun fullPath() = "$path.$extension"
}