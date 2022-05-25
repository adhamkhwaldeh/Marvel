package com.aljawad.sons.mainlibrary.states

data class PoJoByIdStateLoadedSuccessfully<T>(var data: T?) : BaseState.FeaturedState<T>()
