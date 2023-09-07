package devin.astra.pokeapi.data.utils

fun String?.replaceIfNull(replacementValue: String = "") : String {
  return this ?: replacementValue
}