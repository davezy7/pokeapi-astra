package devin.astra.pokeapi.presentation.util

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import com.google.android.material.textfield.TextInputEditText
import java.util.Locale

fun String?.capitalizeEachWord(): String {
  val split = this?.lowercase()?.split(" ")
  return split?.joinToString(separator = " ") { text ->
    text.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
  }.toString()
}

fun TextInputEditText.onDoneActionPressed(action: (String) -> Unit) {
  setOnEditorActionListener { _, actionId, event ->
    if (actionId == EditorInfo.IME_ACTION_SEARCH
      || actionId == EditorInfo.IME_ACTION_DONE
      || event.action == KeyEvent.ACTION_DOWN
      && event.keyCode == KeyEvent.KEYCODE_ENTER
    ) {
      val text = this.text.toString()
      action.invoke(text)
      return@setOnEditorActionListener true
    }
    false
  }
}