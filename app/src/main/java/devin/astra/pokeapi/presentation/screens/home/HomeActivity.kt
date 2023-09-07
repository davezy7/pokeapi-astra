package devin.astra.pokeapi.presentation.screens.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import devin.astra.pokeapi.databinding.ActivityHomeBinding
import devin.astra.pokeapi.presentation.screens.detail.DetailActivity
import devin.astra.pokeapi.presentation.util.onDoneActionPressed
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

  private lateinit var binding: ActivityHomeBinding
  private val viewModel: HomeViewModel by viewModels()
  private val adapter = HomeAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityHomeBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setupCollectors()
    setupAdapter()
    setupAppBar()
  }

  private fun setupAppBar() {
    binding.viewAppbar.apply {
      tieSearch.onDoneActionPressed {
        viewModel.setSearch(it)
      }
      btnSort.setOnClickListener { showSortDialog() }
    }
  }

  private fun setupCollectors() {
    lifecycleScope.launch {
      viewModel.pokeListState
        .flowWithLifecycle(lifecycle, Lifecycle.State.CREATED)
        .collectLatest {
          adapter.submitList(null)
          binding.animator.displayedChild = 1
          adapter.submitList(it) {
            if (!it.isNullOrEmpty()) binding.animator.displayedChild = 0
            else binding.animator.displayedChild = 2
          }
        }
    }
  }

  private fun showSortDialog() {
    val dialog = HomeSortDialog(viewModel.sort.value)
    dialog.setOnClickListener {
      viewModel.setSort(it)
    }
    dialog.show(supportFragmentManager, HomeSortDialog::class.simpleName)
  }

  private fun setupAdapter() {
    binding.rvPokemonList.adapter = adapter
    adapter.setOnItemClicked { navigateToDetail(it) }
  }

  private fun navigateToDetail(name: String) {
    val intent = DetailActivity.newIntent(this, name)
    startActivity(intent)
  }

  companion object {
    fun newIntent(context: Context?): Intent = Intent(context, HomeActivity::class.java)
  }
}