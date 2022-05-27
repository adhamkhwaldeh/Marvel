package com.aljawad.sons.marvel.marvelcore.viewModels


import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.aljawad.sons.marvel.business.CharacterComicsUseCase
import com.aljawad.sons.marvel.dtos.models.CharacterModel
import com.aljawad.sons.marvel.marvelrepository.paging.PagingParamConfig
import com.aljawad.sons.marvel.marvelrepository.repositories.CharacterPagingRepository
import com.aljawad.sons.mainlibrary.base.BaseViewModel
import com.aljawad.sons.mainlibrary.states.BaseState
import com.aljawad.sons.marvel.dtos.models.ComicModel
import com.aljawad.sons.marvel.dtos.responses.DataContainerModel
import com.aljawad.sons.marvel.dtos.responses.DataWrapperModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
//    @ApplicationContext private val context: Context,
    private val userPagingRepository: CharacterPagingRepository,
    private val characterComicsUseCase: CharacterComicsUseCase,
    private val handle: SavedStateHandle
) : BaseViewModel() {

    //region users paging
    private lateinit var _usersFlow: Flow<PagingData<CharacterModel>>
    val usersFlow: Flow<PagingData<CharacterModel>>
        get() = _usersFlow

    var payload = handle.getLiveData(
        "payload",
        PagingParamConfig.initialOffset
    )

    private var _payloadPointer = payload.value!!

    fun getCharacterList() = launchPagingAsync(
        {
            userPagingRepository.getUserPageList(_payloadPointer)
        },
        { flow ->
            _usersFlow = flow
//            .map { pagingData: PagingData<UserModel> ->
//            pagingData
//                            .map { poKo ->
//                        poKo
//                        LocationModel.LocationData(location)
//                    }
//                    .insertSeparators<LocationModel.LocationData, LocationModel> { before, after ->
//                        when {
//                            before == null -> null
//                            after == null -> null
//                            else -> LocationModel.LocationSeparator("Separator: $before-$after")
//                        }
//                    }
//        }
                .cachedIn(viewModelScope)
        },
    )
    //endregion

    //region Character Comics

    private lateinit var _characterComicsFlow: Flow<BaseState<DataWrapperModel<DataContainerModel<MutableList<ComicModel>>>>>
    val characterComicsFlow: Flow<BaseState<DataWrapperModel<DataContainerModel<MutableList<ComicModel>>>>>
        get() = _characterComicsFlow

    fun loadCharacterComics(characterId: Int) { //: Flow<BaseState>
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO)
                .launch {
                    _characterComicsFlow =
                        characterComicsUseCase(characterId)//.map { poKo -> poKo }
                }
        }
    }
    //endregion


}