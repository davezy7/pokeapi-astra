package devin.astra.pokeapi.data.sources.remote

import devin.astra.pokeapi.data.responses.PokemonDetailResponse
import devin.astra.pokeapi.data.responses.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface PokeApiService {

  @GET("pokemon?offset=0")
  suspend fun getPokemonList(
    @Query("limit") limit: Int
  ): Response<PokemonListResponse>

  @GET("pokemon/{name}")
  suspend fun getPokemonDetail(
    @Path("name") pokemonName: String
  ): Response<PokemonDetailResponse>

}