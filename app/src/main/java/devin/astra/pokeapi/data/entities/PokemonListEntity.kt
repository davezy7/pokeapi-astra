package devin.astra.pokeapi.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import devin.astra.pokeapi.data.responses.PokemonListResultResponse
import devin.astra.pokeapi.data.utils.replaceIfNull
import devin.astra.pokeapi.domain.models.PokemonListModel

@Entity(tableName = "pokemons")
data class PokemonListEntity(
  @PrimaryKey(autoGenerate = true) val id: Int?,
  @ColumnInfo(name = "name") val name: String?
) {
  companion object {
    fun fromResponse(data: PokemonListResultResponse): PokemonListEntity {
      return PokemonListEntity(null, data.name.replaceIfNull())
    }

    fun toModel(data: PokemonListEntity): PokemonListModel {
      return PokemonListModel(name = data.name.replaceIfNull())
    }
  }
}