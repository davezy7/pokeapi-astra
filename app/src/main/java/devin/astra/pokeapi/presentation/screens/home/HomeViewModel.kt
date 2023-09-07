package devin.astra.pokeapi.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import devin.astra.pokeapi.domain.repositories.PokemonRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
  private val repository: PokemonRepository
): ViewModel() {

  private val _search = MutableStateFlow("")
  private val _sort = MutableStateFlow("asc")
  val sort = _sort.asStateFlow()

  val pokeListState = _search.flatMapLatest { search ->
    _sort.flatMapLatest { sort ->
      repository.getPokemonList(search, sort)
    }
  }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

  fun setSearch(value: String) {
    _search.value = value
  }

  fun setSort(value: String) {
    _sort.value = value
  }

}