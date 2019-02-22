package me.tumur.portfolio.helpers.databinding

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import me.tumur.portfolio.R

object BindingAdapters {

    @BindingAdapter("avatarUrl")
    @JvmStatic
    fun avatarUrl(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(R.drawable.ic_profile_placeholder)
            .centerCrop()
            .fitCenter()
            .into(imageView)
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun imageUrl(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(R.drawable.ic_placeholder)
            .centerCrop()
            .fitCenter()
            .into(imageView)
    }

    @BindingAdapter("logoPlaceHolder")
    fun logoPlaceHolder(view: ImageView, status: Boolean) {
        view.setImageDrawable(getPlaceholder(status, view.context))
    }

    @BindingAdapter("hideIfFalse")
    fun hideIfFalse(view: View, status: Boolean) {
        view.visibility = if (status) View.VISIBLE else View.GONE
    }

    private fun getPlaceholder(status: Boolean, context: Context): Drawable? = when (status) {
        true -> ContextCompat.getDrawable(context, R.drawable.ic_icon_one_placeholder_one)
        false -> ContextCompat.getDrawable(context, R.drawable.ic_icon_one_placeholder_two)
    }
}