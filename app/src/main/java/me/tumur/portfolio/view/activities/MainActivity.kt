package me.tumur.portfolio.view.activities

import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import me.tumur.portfolio.R
import me.tumur.portfolio.databinding.ActivityMainBinding
import me.tumur.portfolio.extentions.activityBinding
import me.tumur.portfolio.viewmodel.activities.MainViewModel
import org.koin.androidx.viewmodel.ext.viewModel


class MainActivity : AppCompatActivity() {

    // Inject viewmodel
    private val vmodel: MainViewModel by viewModel()

    // Delegate databinding
    private val binding by activityBinding<ActivityMainBinding>(R.layout.activity_main)


    // Screen orientation
    private val isLandscape: Boolean by lazy {
        when (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            true -> true
            false -> false
        }
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bind data to UI
        binding.apply {
            this.lifecycleOwner = this@MainActivity
            this.model = vmodel
        }

        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        when (isLandscape) {
            true -> {
                navigation_side?.itemIconTintList = null
                setupSideMenu(navController)
            }
            false -> {
                navigation_bottom?.itemIconTintList = null
                setupBottomMenu(navController)
            }
        }
    }

    // Setup bottom menu for vertical mode
    private fun setupBottomMenu(navController: NavController) {
        navigation_bottom?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
        NavigationUI.setupActionBarWithNavController(this, navController, null)
    }

    // Setup side menu for horizontal mode
    private fun setupSideMenu(navController: NavController) {
        navigation_side?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
    }

    // Setup back button depends on screen orientation
    override fun onSupportNavigateUp(): Boolean {
        return when (isLandscape) {
            true -> {
                NavigationUI.navigateUp(
                    Navigation.findNavController(this, R.id.nav_host_fragment), drawer_layout
                )
            }
            false -> {
                NavigationUI.navigateUp(
                    Navigation.findNavController(this, R.id.nav_host_fragment), null
                )
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        val navigated = NavigationUI.onNavDestinationSelected(item!!, navController)
        return navigated || super.onOptionsItemSelected(item)
    }
}
