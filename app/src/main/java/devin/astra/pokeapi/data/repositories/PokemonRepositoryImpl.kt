package devin.astra.pokeapi.data.repositories

import devin.astra.pokeapi.data.entities.PokemonListEntity
import devin.astra.pokeapi.data.responses.PokemonDetailResponse
import devin.astra.pokeapi.data.responses.PokemonListResultResponse
import devin.astra.pokeapi.data.sources.local.PokemonDao
import devin.astra.pokeapi.data.sources.remote.PokeApiService
import devin.astra.pokeapi.data.utils.DataConstants
import devin.astra.pokeapi.domain.models.PokemonDetailModel
import devin.astra.pokeapi.domain.models.PokemonListModel
import devin.astra.pokeapi.domain.repositories.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
  private val service: PokeApiService,
  private val dao: PokemonDao
) : PokemonRepository {

  override fun getPokemonList(search: String, sort: String): Flow<List<PokemonListModel>> = flow {
    val localData = getLocalData("", "asc")
    if (localData.isEmpty()) { fetchFromNetwork() }
    val newLocalData = getLocalData(search,sort).map { PokemonListEntity.toModel(it) }
    emit(newLocalData)
  }

  override fun getPokemonDetail(name: String): Flow<PokemonDetailModel> = flow {
    val response = service.getPokemonDetail(name)
    response.body()?.let {
      val data = PokemonDetailResponse.toModel(it)
      emit(data)
    } ?: run { emit(PokemonDetailModel()) }
  }.flowOn(Dispatchers.IO)

  private suspend fun fetchFromNetwork() {
    return withContext(Dispatchers.IO) {
      val response = service.getPokemonList(DataConstants.POKEMON_TOTAL)
      response.body()?.let { body ->
        saveToDatabase(body.results)
      } ?: run { throw HttpException(response) }
    }
  }

  private suspend fun saveToDatabase(data: List<PokemonListResultResponse>?) {
    withContext(Dispatchers.IO) {
      val newData = data?.map { PokemonListEntity.fromResponse(it) } ?: return@withContext
      dao.insertAllPokemon(newData)
    }
  }

  private suspend fun getLocalData(search: String, sort: String): List<PokemonListEntity> {
    return withContext(Dispatchers.IO) { dao.getPokemonList(search, sort) }
  }
}