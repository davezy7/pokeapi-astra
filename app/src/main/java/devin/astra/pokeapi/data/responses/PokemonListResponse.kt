package devin.astra.pokeapi.data.responses

import com.google.gson.annotations.SerializedName
import devin.astra.pokeapi.data.utils.replaceIfNull
import devin.astra.pokeapi.domain.models.PokemonListModel

data class PokemonListResponse(

  @SerializedName("next")
  val next: String? = null,

  @SerializedName("previous")
  val prev: String? = null,

  @SerializedName("results")
  val results: List<PokemonListResultResponse>? = null
)

data class PokemonListResultResponse(

  @SerializedName("name")
  val name: String? = null,

  @SerializedName("url")
  val url: String? = null
) {
  companion object {
    fun toModel(result: PokemonListResultResponse?): PokemonListModel {
      return PokemonListModel(
        result?.name.replaceIfNull(),
        result?.url.replaceIfNull()
      )
    }
  }
}