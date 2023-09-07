package devin.astra.pokeapi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import devin.astra.pokeapi.data.sources.local.PokemonDao
import devin.astra.pokeapi.data.entities.PokemonListEntity

@Database(entities = [PokemonListEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
  abstract fun pokemonDao(): PokemonDao
}