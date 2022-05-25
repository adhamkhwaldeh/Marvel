package com.aljawad.sons.mainlibrary.models

data class ErrorMessage(val data: MutableList<Message>?) {

    fun getErrorsCollections(): String {
        return data?.joinToString(separator = "\n") { it.field + " " + it.message } ?: ""
    }
}

data class Message(val field: String, val message: String) {

}
