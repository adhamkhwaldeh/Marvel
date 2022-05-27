package com.aljawad.sons.marvel.marvelrepository.repositories


import com.aljawad.sons.mainlibrary.extensions.md5
import com.aljawad.sons.marvel.network.apiServices.apiInterfaces.CharacterApi
import com.aljawad.sons.mainlibrary.states.BaseState
import com.aljawad.sons.mainlibrary.states.requestBlockingById
import com.aljawad.sons.marvel.dtos.models.ComicModel
import com.aljawad.sons.marvel.dtos.responses.DataContainerModel
import com.aljawad.sons.marvel.dtos.responses.DataWrapperModel
import com.aljawad.sons.marvel.marvelrepository.BuildConfig
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CharacterRepository @Inject constructor(
    private var apiService: CharacterApi
) {
    suspend fun loadCharacterComics(characterId: Int): Flow<BaseState<DataWrapperModel<DataContainerModel<MutableList<ComicModel>>>>> {
        val ts = System.currentTimeMillis()
        val hash = (ts.toString() + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY).md5()

        return requestBlockingById {
            apiService.getCharacterComicList(
                characterId = characterId,
                ts = ts,
                apikey = BuildConfig.PUBLIC_KEY,
                hash = hash,
            )
        }

    }


}