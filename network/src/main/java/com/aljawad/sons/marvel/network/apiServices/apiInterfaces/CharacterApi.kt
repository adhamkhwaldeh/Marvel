package com.aljawad.sons.marvel.network.apiServices.apiInterfaces


import com.aljawad.sons.dtos.models.UserModel
import com.aljawad.sons.dtos.response.UserListResponse
import retrofit2.Response
import retrofit2.http.*

interface CharacterApi {

    @GET("users")
    suspend fun getCharacterList(@Query("page") page: Int): UserListResponse

    @GET("v1/public/characters/{characterId}/comics")
    suspend fun getCharacterComicList(@Query("characterId") characterId: Int): UserListResponse

}