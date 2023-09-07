package devin.astra.pokeapi.data.sources.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import devin.astra.pokeapi.data.responses.PokemonListResultResponse
import devin.astra.pokeapi.data.sources.remote.PokeApiService
import devin.astra.pokeapi.data.utils.DataConstants
import retrofit2.HttpException
import javax.inject.Inject

class PokemonListPagingSource @Inject constructor(
  private val service: PokeApiService
) : PagingSource<Int, PokemonListResultResponse>() {

  override fun getRefreshKey(state: PagingState<Int, PokemonListResultResponse>): Int? {
    return state.anchorPosition?.let { anchorPos ->
      state.closestPageToPosition(anchorPos)?.prevKey?.plus(1)
        ?: state.closestPageToPosition(anchorPos)?.nextKey?.minus(1)
    }
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonListResultResponse> {
    val offset = params.key ?: DataConstants.PAGING_OFFSET
    return try {
      val query = hashMapOf<String, String>()
      query["offset"] = offset.toString()
      query["limit"] = DataConstants.PAGING_LIMIT.toString()

      val response = service.getPokemonList(0)
      response.body()?.let { data ->
        LoadResult.Page(
          data = data.results ?: listOf(),
          prevKey = if (offset == 0) null else offset - 50,
          nextKey = if (data.results?.size != DataConstants.PAGING_LIMIT) null else offset + 50
        )
      } ?: LoadResult.Error(HttpException(response))
    } catch (t: Throwable) {
      t.printStackTrace()
      LoadResult.Error(t)
    }
  }

  companion object {
    fun getPaging(service: PokeApiService): Pager<Int, PokemonListResultResponse> {
      return Pager(
        config = PagingConfig(
          pageSize = DataConstants.PAGING_LIMIT,
          initialLoadSize = DataConstants.PAGING_LIMIT
        ),
        pagingSourceFactory = { PokemonListPagingSource(service) }
      )
    }
  }
}