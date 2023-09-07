package devin.astra.pokeapi.data.sources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import devin.astra.pokeapi.data.entities.PokemonListEntity

@Dao
interface PokemonDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAllPokemon(data: List<PokemonListEntity>)

  @Query("SELECT p.* FROM pokemons p " +
      "WHERE LOWER(:search) = '' OR :search LIKE p.name " +
      "ORDER BY " +
      "CASE WHEN LOWER(:sort) = 'asc' THEN p.name END ASC, " +
      "CASE WHEN LOWER(:sort) = 'desc' THEN p.name END DESC")
  suspend fun getPokemonList(search: String, sort: String): List<PokemonListEntity>
}