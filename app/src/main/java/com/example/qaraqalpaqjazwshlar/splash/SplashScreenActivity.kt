package com.example.qaraqalpaqjazwshlar.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.qaraqalpaqjazwshlar.R
import com.example.qaraqalpaqjazwshlar.main.MainActivity
import com.example.qaraqalpaqjazwshlar.settings.SettingsFragment

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferences = getSharedPreferences(SettingsFragment.FILE_SETTINGS, Context.MODE_PRIVATE)
        if (preferences.getBoolean(SettingsFragment.KEY_NIGHT_MODE, false))
            AppCompatDelegate.setDefaultNightMode(SettingsFragment.DAY_NIGHT)
        else AppCompatDelegate.setDefaultNightMode(SettingsFragment.DAY_LIGHT)
        setContentView(R.layout.splash_activity)
        window.setFlags(
                WindowManager.LayoutParams.ANIMATION_CHANGED,
                WindowManager.LayoutParams.ANIMATION_CHANGED
        )
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },1500)
    }
}