package com.aljawad.sons.mainlibrary.base

import com.aljawad.sons.mainlibrary.states.BaseState
import kotlinx.coroutines.flow.Flow

abstract class BaseFlowUseCase<out Type, in Params>() {

    abstract suspend fun run(params: Params): Flow<BaseState<Type>>

    suspend operator fun invoke(params: Params): Flow<BaseState<Type>> {
        return run(params)
    }

    class None
}
