package me.tumur.portfolio.view.activities

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import kotlinx.android.synthetic.main.activity_splash.*
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

        // Icons
        if (vmodel.getIconOne() == false) {
            showIcon(R.drawable.ic_icon_one, iv_icon_one, false)
        } else {
            showPlaceHolder(R.drawable.ic_icon_one_placeholder_two, iv_icon_one)
            vmodel.setIconOne(true)
        }
        if (vmodel.getIconTwo() == false) showIcon(R.drawable.ic_icon_two, iv_icon_two, true)

        // Observer
        vmodel.getIcons().observe(this, Observer { item ->
            if (item == 2) routeToMainActivity()
        })

    }

    // Route to MainActivity
    private fun routeToMainActivity() {
        vmodel.setFirstRun(false)
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        overridePendingTransition(me.tumur.portfolio.R.anim.fade_in, me.tumur.portfolio.R.anim.fade_out)
        finish()
    }

    // Create animated icon and set to imageView
    private fun showIcon(
        drawableIcon: Int,
        imageView: AppCompatImageView,
        loop: Boolean
    ): AnimatedVectorDrawableCompat {
        val icon = AnimatedVectorDrawableCompat.create(this, drawableIcon)!!
        icon.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
            override fun onAnimationEnd(drawable: Drawable?) {
                when (loop) {
                    true -> {
                        if (vmodel.getAnimation() == 0) {
                            imageView.post { icon.stop() }
                            vmodel.setIconTwo(true)
                        } else {
                            imageView.post { icon.start() }
                            vmodel.decAnimation()
                        }
                    }
                    false -> {
                        vmodel.setIconOne(true)
                    }
                }
            }
        })
        imageView.setImageDrawable(icon)
        icon.start()
        return icon
    }

    // Set placeholder when the screen rotates while animation
    private fun showPlaceHolder(drawableIcon: Int, imageView: AppCompatImageView) {
        imageView.setImageResource(drawableIcon)
    }
}