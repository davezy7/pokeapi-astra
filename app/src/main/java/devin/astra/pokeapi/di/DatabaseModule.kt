package devin.astra.pokeapi.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import devin.astra.pokeapi.data.database.AppDatabase
import devin.astra.pokeapi.data.sources.local.PokemonDao
import devin.astra.pokeapi.data.utils.DataConstants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

  @Provides
  @Singleton
  fun provideAppDatabase(
    @ApplicationContext context: Context
  ) : AppDatabase {
    return Room.databaseBuilder(
      context,
      AppDatabase::class.java, DataConstants.DB_NAME
    ).build()
  }

  @Provides
  @Singleton
  fun providePokemonDao(
    database: AppDatabase
  ) : PokemonDao {
    return database.pokemonDao()
  }

}