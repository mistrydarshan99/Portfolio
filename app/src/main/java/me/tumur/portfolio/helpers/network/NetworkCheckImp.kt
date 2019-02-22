package me.tumur.portfolio.helpers.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkCheckImp(private val context: Context) : NetworkCheck {

    override fun getConnection(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }
}