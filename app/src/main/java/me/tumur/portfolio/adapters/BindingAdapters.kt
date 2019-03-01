package me.tumur.portfolio.adapters

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.view.doOnLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

/**
 * Loading image from the network or cache with placeholder and error images
 */
@BindingAdapter("imageUrl", "placeholder", "error", requireAll = false)
fun imageUrl(imageView: ImageView, url: String?, placeholderDrawable: Drawable?, errorDrawable: Drawable?) {

    // Build requestOptions for Glide
    val requestOptions = RequestOptions()
    if (placeholderDrawable != null) requestOptions.placeholder(placeholderDrawable)
    if (errorDrawable != null) requestOptions.error(errorDrawable)

    // Load image into imageView
    if (url != null)
        imageView.doOnLayout {
            Glide.with(imageView.context)
                .load(url)
                .apply(requestOptions)
                .centerCrop()
                .fitCenter()
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }
}

@BindingAdapter("hideIfFalse")
fun hideIfFalse(view: View, status: Boolean) {
    view.visibility = if (status) View.VISIBLE else View.GONE
}

@BindingAdapter("showIfFalse")
fun showIfFalse(view: View, status: Boolean) {
    view.visibility = if (status) View.GONE else View.VISIBLE
}