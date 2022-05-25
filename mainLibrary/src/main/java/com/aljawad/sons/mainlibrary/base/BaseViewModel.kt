package com.aljawad.sons.mainlibrary.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


abstract class BaseViewModel : ViewModel() {
    inline fun <T> launchPagingAsync(
        crossinline execute: suspend () -> Flow<T>,
        crossinline onSuccess: (Flow<T>) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val result = execute()
                onSuccess(result)
            } catch (ex: Exception) {
//                errorPagingMessage.value = ex.message
            }
        }
    }
}