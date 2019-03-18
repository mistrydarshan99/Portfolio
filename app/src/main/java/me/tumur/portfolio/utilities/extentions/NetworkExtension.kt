package me.tumur.portfolio.utilities.extentions

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.RequiresPermission

/**
 * Checks network access
 */

@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
fun Any.isConnected(context: Context): Boolean {
    val connectivityManager = context
        .getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.let {
        val netInfo = it.activeNetworkInfo
        netInfo?.let {
            if (it.isConnected) return true
        }
    }
    return false
}