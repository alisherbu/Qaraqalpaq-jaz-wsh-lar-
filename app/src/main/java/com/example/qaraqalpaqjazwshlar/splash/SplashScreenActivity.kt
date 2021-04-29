package com.example.qaraqalpaqjazwshlar.activity.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.qaraqalpaqjazwshlar.R
import com.example.qaraqalpaqjazwshlar.activity.MainActivity

@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity(), SplashView {
    private val presenter = SplashPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        presenter.setFlags()
    }

    override fun goToMainScreen() {
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}