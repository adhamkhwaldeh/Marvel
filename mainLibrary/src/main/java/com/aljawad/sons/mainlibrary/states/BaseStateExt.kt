package com.aljawad.sons.mainlibrary.states

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


suspend fun <T> requestBlockingById(flowable: suspend () -> T): Flow<BaseState<T>> = flow {
    this.emit(BaseState.Loading<T>())
    Result.runCatching { flowable.invoke() }.fold(
        onSuccess = {
            this.emit(BaseState.LoadingDismiss<T>())
            this.emit(PoJoByIdStateLoadedSuccessfully<T>(it))
        },
        onFailure = { ex ->
            this.emit(BaseState.LoadingDismiss<T>())
            this.emit(BaseState.getStateByThrowable<T>(ex))
        },
    )
}
