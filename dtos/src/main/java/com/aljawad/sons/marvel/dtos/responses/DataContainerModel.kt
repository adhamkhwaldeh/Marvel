package com.aljawad.sons.marvel.dtos.responses

data class DataContainerModel<T>(
    var offset: Int,
    var limit: Int,
    var total: Int,
    var count: Int,
    var results: T
) {
}