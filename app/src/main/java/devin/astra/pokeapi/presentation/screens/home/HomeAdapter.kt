package devin.astra.pokeapi.presentation.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import devin.astra.pokeapi.databinding.ItemPokemonListBinding
import devin.astra.pokeapi.domain.models.PokemonListModel
import devin.astra.pokeapi.presentation.util.capitalizeEachWord

class HomeAdapter : ListAdapter<PokemonListModel, HomeAdapter.HomeAdapterViewHolder>(
  COMPARATOR
) {

  private var onItemClicked: ((String) -> Unit)? = null

  override fun onBindViewHolder(holder: HomeAdapterViewHolder, position: Int) {
    getItem(position)?.let { holder.bind(it) }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapterViewHolder {
    return HomeAdapterViewHolder(
      ItemPokemonListBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )
  }

  fun setOnItemClicked(listener: (String) -> Unit) {
    onItemClicked = listener
  }

  inner class HomeAdapterViewHolder(
    private val binding: ItemPokemonListBinding
  ) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: PokemonListModel) {
      binding.tvName.text = item.name.capitalizeEachWord()
      binding.root.setOnClickListener { onItemClicked?.invoke(item.name) }
    }
  }

  companion object {
    private val COMPARATOR = object : DiffUtil.ItemCallback<PokemonListModel>() {
      override fun areItemsTheSame(oldItem: PokemonListModel, newItem: PokemonListModel): Boolean {
        return oldItem.name == newItem.name
      }

      override fun areContentsTheSame(
        oldItem: PokemonListModel,
        newItem: PokemonListModel
      ): Boolean {
        return oldItem == newItem
      }
    }
  }
}