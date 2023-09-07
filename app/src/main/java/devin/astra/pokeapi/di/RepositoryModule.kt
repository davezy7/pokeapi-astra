package devin.astra.pokeapi.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import devin.astra.pokeapi.data.repositories.PokemonRepositoryImpl
import devin.astra.pokeapi.domain.repositories.PokemonRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

  @ViewModelScoped
  @Binds
  abstract fun bindsPokemonRepository(impl: PokemonRepositoryImpl): PokemonRepository

}