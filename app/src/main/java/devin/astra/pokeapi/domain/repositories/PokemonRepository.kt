package devin.astra.pokeapi.domain.repositories

import androidx.paging.PagingData
import devin.astra.pokeapi.domain.models.PokemonDetailModel
import devin.astra.pokeapi.domain.models.PokemonListModel
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

  fun getPokemonList(search: String, sort: String): Flow<List<PokemonListModel>>

  fun getPokemonDetail(name: String): Flow<PokemonDetailModel>
}