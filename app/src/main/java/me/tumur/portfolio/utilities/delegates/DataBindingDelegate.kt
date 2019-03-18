package me.tumur.portfolio.utilities.delegates

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal class ActivityBindingProperty<out T : ViewDataBinding>(@LayoutRes private val resId: Int) :
    ReadOnlyProperty<Activity, T> {

    private var binding: T? = null

    override operator fun getValue(thisRef: Activity, property: KProperty<*>): T {
        return binding ?: createActivityBinding(thisRef).also { binding = it }
    }

    private fun createActivityBinding(activity: Activity): T {
        return DataBindingUtil.setContentView(activity, resId)
    }
}
