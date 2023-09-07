package devin.astra.pokeapi.presentation.screens.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import devin.astra.pokeapi.databinding.ItemAbilityListBinding
import devin.astra.pokeapi.presentation.util.capitalizeEachWord

class DetailAdapter : ListAdapter<String, DetailAdapter.DetailAdapterViewHolder>(COMPARATOR) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAdapterViewHolder {
    return DetailAdapterViewHolder(
      ItemAbilityListBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: DetailAdapterViewHolder, position: Int) {
    getItem(position)?.let { holder.bind(it) }
  }

  inner class DetailAdapterViewHolder(
    private val binding: ItemAbilityListBinding
  ) : ViewHolder(binding.root) {
    fun bind(item: String) {
      binding.tvAbilityName.text = item.capitalizeEachWord()
    }
  }

  companion object {
    private val COMPARATOR = object : DiffUtil.ItemCallback<String>() {
      override fun areItemsTheSame(
        oldItem: String,
        newItem: String
      ): Boolean {
        return oldItem == newItem
      }

      override fun areContentsTheSame(
        oldItem: String,
        newItem: String
      ): Boolean {
        return oldItem == newItem
      }
    }
  }
}