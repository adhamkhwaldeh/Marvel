package com.aljawad.sons.marvel.marvelrepository.paging.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.aljawad.sons.mainlibrary.extensions.md5
import com.aljawad.sons.marvel.network.apiServices.apiInterfaces.CharacterApi
import com.aljawad.sons.marvel.dtos.models.CharacterModel
import com.aljawad.sons.marvel.marvelrepository.BuildConfig
import com.aljawad.sons.marvel.marvelrepository.paging.PagingParamConfig
import java.sql.Timestamp

class CharacterPagingDataSource(
    private val characterApi: CharacterApi,
    private var payload: Int
) : PagingSource<Int, CharacterModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        val pageNumber = params.key ?: PagingParamConfig.initialOffset
        return try {
            val ts = System.currentTimeMillis()
            val hash = ts.toString() + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY

            val response = characterApi.getCharacterList(
                ts = ts,
                apikey = BuildConfig.PUBLIC_KEY,
                hash = hash.md5(),
                pageNumber,
                PagingParamConfig.pageSize
            )

            val data = response.data.results

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

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int {
//        payload.set(PagingParamConfig.initialOffset)
        payload = PagingParamConfig.initialOffset
        return PagingParamConfig.initialOffset
    }

    override val keyReuseSupported: Boolean
        get() = true//super.keyReuseSupported


}