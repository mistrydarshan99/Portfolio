package me.tumur.portfolio.view.activities

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import kotlinx.android.synthetic.main.activity_splash.*
import me.tumur.portfolio.R
import me.tumur.portfolio.databinding.ActivitySplashBinding
import me.tumur.portfolio.viewmodel.activities.SplashViewModel
import org.koin.androidx.viewmodel.ext.viewModel


class SplashActivity : AppCompatActivity() {

    // Inject
    private val vModel: SplashViewModel by viewModel()

    // Databinding
    private val binding: ActivitySplashBinding by lazy {
        DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set UI layout
        //setContentView(R.layout.activity_splash)

        // Bind data to uiViewModel
        binding.apply {
            this.lifecycleOwner = this@SplashActivity
            this.data = vModel
        }

        // Icons
        if (vModel.getIconOne() == false) {
            showIcon(R.drawable.ic_icon_one, iv_icon_one, false)
        } else {
            showPlaceHolder(R.drawable.ic_icon_one_placeholder_two, iv_icon_one)
            vModel.setIconOne(true)
        }
        if (vModel.getIconTwo() == false) showIcon(R.drawable.ic_icon_two, iv_icon_two, true)

        // Observer
        vModel.getIcons().observe(this, Observer { item ->
            if (item == 2) routeToMainActivity()
        })

    }

    // Route to MainActivity
    private fun routeToMainActivity() {
        vModel.setFirstRun(false)
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
                        if (vModel.getAnimation() == 0) {
                            imageView.post { icon.stop() }
                            vModel.setIconTwo(true)
                        } else {
                            imageView.post { icon.start() }
                            vModel.decAnimation()
                        }
                    }
                    false -> {
                        vModel.setIconOne(true)
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