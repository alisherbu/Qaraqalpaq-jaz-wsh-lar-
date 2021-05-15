package com.example.qaraqalpaqjazwshlar

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_settings.*

class Settings() : Fragment(R.layout.activity_settings) {
    companion object {
         const val dayNight = AppCompatDelegate.MODE_NIGHT_YES
        const val dayLight = AppCompatDelegate.MODE_NIGHT_NO
    }
    lateinit var preferences: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        preferences = requireContext().getSharedPreferences("settings", Context.MODE_PRIVATE)
        nightMode.isChecked=preferences.getBoolean("night_mode", nightMode.isChecked)
        super.onViewCreated(view, savedInstanceState)
        nightMode.setOnCheckedChangeListener { buttonView, isChecked ->
            preferences.edit().putBoolean("night_mode", isChecked).apply()
            if (isChecked) AppCompatDelegate.setDefaultNightMode(dayNight)
            else AppCompatDelegate.setDefaultNightMode(dayLight)
        }
    }
}