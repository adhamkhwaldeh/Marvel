package com.aljawad.sons.marvel.marvelrepository


import com.aljawad.sons.marvel.marvelrepository.repositories.CharacterPagingRepository
import com.aljawad.sons.marvel.marvelrepository.repositories.CharacterPagingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryPagingModule {

    @Binds
    abstract fun bindUserPagingRepository(
        repository: CharacterPagingRepositoryImpl
    ): CharacterPagingRepository

}