package devin.astra.pokeapi.presentation.screens.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import devin.astra.pokeapi.R
import devin.astra.pokeapi.databinding.DialogSortBinding

class HomeSortDialog(private val sort: String) : DialogFragment() {

  private lateinit var binding: DialogSortBinding
  private var onClicked: ((String) -> Unit)? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setStyle(STYLE_NORMAL, R.style.BaseDialog)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DialogSortBinding.inflate(inflater)
    return binding.root
  }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.apply {
      if (sort == "asc") containerAsc.setSortSelected()
      else containerDesc.setSortSelected()
      containerAsc.setOnClickListener {
        onClicked?.invoke("asc")
        dismiss()
      }
      containerDesc.setOnClickListener {
        onClicked?.invoke("desc")
        dismiss()
      }
    }
  }

  override fun onStart() {
    super.onStart()
    val dialog: Dialog? = dialog
    if (dialog != null) {
      val width = ViewGroup.LayoutParams.MATCH_PARENT
      val height = ViewGroup.LayoutParams.WRAP_CONTENT
      dialog.window?.setLayout(width, height)
    }
  }

  override fun show(fm: FragmentManager, tag: String?) {
    try {
      super.show(fm, tag)
    } catch (ex: Exception) {
      showAllowingStateLoss(fm, tag)
    }
  }

  private fun showAllowingStateLoss(fm: FragmentManager, tag: String?) {
    try {
      fm.beginTransaction()
        .add(this, tag)
        .commitAllowingStateLoss()
    } catch (ex: Exception) {
      ex.printStackTrace()
    }
  }

  fun setOnClickListener(listener: (String) -> Unit) {
    onClicked = listener
  }

  private fun View.setSortSelected() {
    this.background = ContextCompat.getDrawable(this.context, R.drawable.bg_sort_selection)
  }

}