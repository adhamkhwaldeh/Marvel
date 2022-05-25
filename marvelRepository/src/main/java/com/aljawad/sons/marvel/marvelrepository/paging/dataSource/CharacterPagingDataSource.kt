package com.aljawad.sons.marvel.marvelrepository.paging.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aljawad.sons.network.apiServices.apiInterfaces.UserApi
import com.aljawad.sons.dtos.models.UserModel
import com.aljawad.sons.gorestrepository.paging.PagingParamConfig

class CharacterPagingDataSource(
    private val userApi: UserApi,
    private var payload: Int
) : PagingSource<Int, UserModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> {
        val pageNumber = params.key ?: PagingParamConfig.initialOffset
        return try {
            val response = userApi.getUserList(pageNumber)

            val data = response.data

            val nextPagePayload = params.key
            nextPagePayload?.inc()

            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = if (data.isNullOrEmpty()) null else nextPagePayload
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int {
//        payload.set(PagingParamConfig.initialOffset)
        payload = PagingParamConfig.initialOffset
        return PagingParamConfig.initialOffset
    }

    override val keyReuseSupported: Boolean
        get() = true//super.keyReuseSupported


}