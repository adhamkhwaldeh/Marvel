package com.aljawad.sons.marvel.network.apiServices.apiInterfaces


import com.aljawad.sons.marvel.dtos.models.CharacterModel
import com.aljawad.sons.marvel.dtos.models.ComicModel
import com.aljawad.sons.marvel.dtos.responses.DataContainerModel
import com.aljawad.sons.marvel.dtos.responses.DataWrapperModel
import retrofit2.http.*

interface CharacterApi {

    @GET("v1/public/characters")
    suspend fun getCharacterList(
        @Query("ts") ts: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): DataWrapperModel<DataContainerModel<MutableList<CharacterModel>>>

    @GET("v1/public/characters/{characterId}/comics")
    suspend fun getCharacterComicList(
        @Path("characterId") characterId: Int,
        @Query("ts") ts: Long,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
    ): DataWrapperModel<DataContainerModel<MutableList<ComicModel>>>


}