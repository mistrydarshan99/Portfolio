package me.tumur.portfolio.utilities.extentions

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import me.tumur.portfolio.utilities.delegates.ActivityBindingProperty

internal fun <T : ViewDataBinding> activityBinding(@LayoutRes resId: Int) =
    ActivityBindingProperty<T>(resId)