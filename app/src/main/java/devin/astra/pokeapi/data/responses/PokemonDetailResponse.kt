package devin.astra.pokeapi.data.responses

import android.util.Log
import com.google.gson.annotations.SerializedName
import devin.astra.pokeapi.data.utils.replaceIfNull
import devin.astra.pokeapi.domain.models.PokemonDetailModel

data class PokemonDetailResponse(
  @SerializedName("name")
  val name: String? = null,
  @SerializedName("sprites")
  val sprites: PokemonSpriteResponse? = null,
  @SerializedName("abilities")
  val abilities: List<PokemonAbilityListResponse>? = null
) {
  companion object {
    fun toModel(response: PokemonDetailResponse?): PokemonDetailModel {
      val pokemonSprite = response?.sprites?.other?.officialArtwork?.frontDefault
        ?: response?.sprites?.frontDefault.replaceIfNull()
      return PokemonDetailModel(
        name = response?.name.replaceIfNull(),
        sprite = pokemonSprite,
        abilities = response?.abilities?.map { it.ability?.name.replaceIfNull() } ?: listOf()
      )
    }
  }
}

data class PokemonSpriteResponse(
  @SerializedName("front_default")
  val frontDefault: String? = null,
  @SerializedName("other")
  val other: PokemonOtherSpriteResponse? = null
)

data class PokemonOtherSpriteResponse(
  @SerializedName("official-artwork")
  val officialArtwork: PokemonOfficialArtworkSpriteResponse? = null
)

data class PokemonOfficialArtworkSpriteResponse(
  @SerializedName("front_default")
  val frontDefault: String? = null
)

data class PokemonAbilityListResponse(
  @SerializedName("ability")
  val ability: PokemonAbilityResponse? = null,
)

data class PokemonAbilityResponse(
  @SerializedName("name")
  val name: String? = null,
)
