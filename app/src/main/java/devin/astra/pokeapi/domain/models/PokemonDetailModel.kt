package devin.astra.pokeapi.domain.models

data class PokemonDetailModel(
  val name: String = "",
  val sprite: String = "",
  val abilities: List<String> = listOf()
)
