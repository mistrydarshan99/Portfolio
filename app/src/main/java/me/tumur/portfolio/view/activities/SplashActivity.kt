package me.tumur.portfolio.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import me.tumur.portfolio.R
import me.tumur.portfolio.databinding.ActivitySplashBinding
import me.tumur.portfolio.extentions.activityBinding
import me.tumur.portfolio.viewmodel.activities.SplashViewModel
import org.koin.androidx.viewmodel.ext.viewModel


class SplashActivity : AppCompatActivity() {

    // Inject viewModel
    private val vmodel: SplashViewModel by viewModel()

    // Delegate databinding
    private val binding by activityBinding<ActivitySplashBinding>(R.layout.activity_splash)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bind data to UI
        binding.apply {
            this.lifecycleOwner = this@SplashActivity
            this.model = vmodel
        }

        // Observer for routeToMainActivity
        vmodel.route.observe(this, Observer { item ->
            if (item) routeToMainActivity()
        })
    }

    // Route to MainActivity
    private fun routeToMainActivity() {
        // Set false for first run
        vmodel.setFirstRun()
        // Route
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        finish()
    }
}