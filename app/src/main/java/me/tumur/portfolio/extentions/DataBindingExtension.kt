package me.tumur.portfolio.extentions

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import me.tumur.portfolio.delegates.ActivityBindingProperty

internal fun <T : ViewDataBinding> activityBinding(@LayoutRes resId: Int) = ActivityBindingProperty<T>(resId)