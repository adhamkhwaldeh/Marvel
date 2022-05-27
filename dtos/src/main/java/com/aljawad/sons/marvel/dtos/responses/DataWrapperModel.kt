package com.aljawad.sons.marvel.dtos.responses

data class DataWrapperModel<T>(
    var code: Int?,
    var status: String?,
    var copyright: String?,
    var attributionText: String?,
    var attributionHTML: String?,
    var data: T,
    var etag: String?
)
