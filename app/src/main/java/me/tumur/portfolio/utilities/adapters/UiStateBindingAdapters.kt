package me.tumur.portfolio.utilities.adapters

import android.view.View
import androidx.databinding.BindingAdapter
import me.tumur.portfolio.utilities.states.*

@BindingAdapter("uiScreenLoading")
fun setUiScreenLoading(view: View, uiState: UIState) {
    view.visibility = when (uiState) {
        Loading -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("uiScreenSuccess")
fun setUiScreenSuccess(view: View, uiState: UIState) {
    view.visibility = when (uiState) {
        Success -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("uiScreenEmpty")
fun setUiScreenEmpty(view: View, uiState: UIState) {
    view.visibility = when (uiState) {
        Empty -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("uiScreenError")
fun setUiScreenError(view: View, uiState: UIState) {
    view.visibility = when (uiState) {
        is Error -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("uiScreenNoNetwork")
fun setUiScreenNoNetwork(view: View, uiState: UIState) {
    view.visibility = when (uiState) {
        is NoNetwork -> View.VISIBLE
        else -> View.GONE
    }
}