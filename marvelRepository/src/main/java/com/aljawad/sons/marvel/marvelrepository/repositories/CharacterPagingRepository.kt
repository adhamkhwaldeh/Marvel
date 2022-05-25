package com.aljawad.sons.marvel.marvelrepository.repositories

import androidx.paging.PagingData
import com.aljawad.sons.dtos.models.UserModel
import kotlinx.coroutines.flow.Flow

interface CharacterPagingRepository {
    //    fun getUserPageList(payload: KMutableProperty0<Int>): Flow<PagingData<UserModel>>
    fun getUserPageList(payload: Int): Flow<PagingData<UserModel>>
}