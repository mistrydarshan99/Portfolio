package me.tumur.portfolio.adapters

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.view.doOnLayout
import androidx.databinding.BindingAdapter
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
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
                .centerCrop()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }
}

/**
 * Create animated vector drawable with optional loop value and set into image view
 */

@BindingAdapter("avdIcon", "loop")
fun setAvdIcon(view: ImageView, @DrawableRes icon: Int, loop: Boolean?) {
    val avdIcon = AnimatedVectorDrawableCompat.create(view.context, icon)!!
    avdIcon.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
        override fun onAnimationEnd(drawable: Drawable?) {
            if (loop == true && loop != null) avdIcon.start()
        }
    })
    view.setImageDrawable(avdIcon)
    avdIcon.start()
}

@BindingAdapter("hideIfFalse")
fun hideIfFalse(view: View, status: Boolean) {
    view.visibility = if (status) View.VISIBLE else View.GONE
}

@BindingAdapter("showIfFalse")
fun showIfFalse(view: View, status: Boolean) {
    view.visibility = if (status) View.GONE else View.VISIBLE
}