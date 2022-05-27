package com.aljawad.sons.marvel.marvelrepository.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aljawad.sons.marvel.network.apiServices.apiInterfaces.CharacterApi
import com.aljawad.sons.marvel.dtos.models.CharacterModel
import com.aljawad.sons.marvel.marvelrepository.paging.PagingParamConfig
import com.aljawad.sons.marvel.marvelrepository.paging.dataSource.CharacterPagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterPagingRepositoryImpl @Inject constructor(private val service: CharacterApi) :
    CharacterPagingRepository {


    @ExperimentalPagingApi
    override fun getUserPageList(payload: Int): Flow<PagingData<CharacterModel>> = Pager(
        config = PagingConfig(
            pageSize = PagingParamConfig.pageSize,
            prefetchDistance = PagingParamConfig.prefetchDistance,
            enablePlaceholders = PagingParamConfig.enablePlaceholders,
            initialLoadSize = PagingParamConfig.initialLoadSize,
        ),
        pagingSourceFactory = { CharacterPagingDataSource(service, payload) },
        initialKey = payload,
        remoteMediator = null

    ).flow

}