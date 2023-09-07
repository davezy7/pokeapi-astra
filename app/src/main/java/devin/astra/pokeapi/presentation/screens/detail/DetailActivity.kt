package devin.astra.pokeapi.presentation.screens.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import devin.astra.pokeapi.BuildConfig
import devin.astra.pokeapi.databinding.ActivityDetailBinding
import devin.astra.pokeapi.domain.models.PokemonDetailModel
import devin.astra.pokeapi.presentation.util.capitalizeEachWord
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

  private lateinit var binding: ActivityDetailBinding
  private val viewModel: DetailViewModel by viewModels()
  private val adapter = DetailAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityDetailBinding.inflate(layoutInflater)
    setContentView(binding.root)

    loadExtras()
    setupCollector()
  }

  private fun loadExtras() {
    intent?.getStringExtra(POKEMON_NAME)?.let {
      viewModel.setPokemonName(it)
    } ?: finish()
  }

  private fun setupCollector() {
    lifecycleScope.launch {
      viewModel.pokemonDetailState
        .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
        .collectLatest { data ->
          data?.let { setupView(it) }
        }
    }
  }

  private fun setupView(data: PokemonDetailModel) {

    binding.apply {
      binding.rvAbilityList.adapter = adapter
      tvName.text = data.name.capitalizeEachWord()
      ivSprite.load(data.sprite)
      adapter.submitList(data.abilities)
    }
  }

  companion object {

    private const val POKEMON_NAME = BuildConfig.APPLICATION_ID + "_POKEMON_NAME"

    fun newIntent(context: Context?, name: String): Intent =
      Intent(context, DetailActivity::class.java).apply {
        putExtra(POKEMON_NAME, name)
      }
  }
}