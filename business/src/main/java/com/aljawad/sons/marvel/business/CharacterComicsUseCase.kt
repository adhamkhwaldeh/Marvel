package com.aljawad.sons.marvel.business


import com.aljawad.sons.marvel.marvelrepository.repositories.CharacterRepository
import com.aljawad.sons.mainlibrary.base.BaseFlowUseCase
import com.aljawad.sons.mainlibrary.states.BaseState
import com.aljawad.sons.marvel.dtos.models.ComicModel
import com.aljawad.sons.marvel.dtos.responses.DataContainerModel
import com.aljawad.sons.marvel.dtos.responses.DataWrapperModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CharacterComicsUseCase @Inject constructor(
    private var repo: CharacterRepository
) : BaseFlowUseCase<DataWrapperModel<DataContainerModel<MutableList<ComicModel>>>, Int>() {

    override suspend fun run(params: Int): Flow<BaseState<DataWrapperModel<DataContainerModel<MutableList<ComicModel>>>>> {
        return repo.loadCharacterComics(params)
    }

}