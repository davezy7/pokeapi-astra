package devin.astra.pokeapi.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import devin.astra.pokeapi.domain.repositories.PokemonRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class DetailViewModel @Inject constructor(
  private val repository: PokemonRepository
) : ViewModel() {


  private val _pokemonName = MutableStateFlow("")

  val pokemonDetailState = _pokemonName.flatMapLatest {
    repository.getPokemonDetail(it)
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

  fun setPokemonName(value: String) {
    _pokemonName.value = value
  }

}