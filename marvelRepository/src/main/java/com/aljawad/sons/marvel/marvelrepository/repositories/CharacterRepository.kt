package com.aljawad.sons.marvel.marvelrepository.repositories


import com.aljawad.sons.network.apiServices.apiInterfaces.CharacterApi
import com.aljawad.sons.dtos.models.UserModel
import com.aljawad.sons.mainlibrary.states.BaseState
import com.aljawad.sons.mainlibrary.states.requestBlockingById
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CharacterRepository @Inject constructor(
    private var apiService: CharacterApi
) {

    suspend fun createUser(userModel: UserModel): Flow<BaseState<UserModel>> {
        return requestBlockingById { apiService.createUser(userModel) }
    }

    suspend fun deleteUser(userId: Int): Flow<BaseState<Any?>> {
        return requestBlockingById<Any?>(flowable = { apiService.deleteUser(userId) })
    }

}