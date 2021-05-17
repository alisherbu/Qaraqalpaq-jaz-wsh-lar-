package com.example.qaraqalpaqjazwshlar.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqjazwshlar.R
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    companion object {
        const val DAY_NIGHT = AppCompatDelegate.MODE_NIGHT_YES
        const val DAY_LIGHT = AppCompatDelegate.MODE_NIGHT_NO
        const val FILE_SETTINGS = "settings"
        const val KEY_NIGHT_MODE = "night_mode"
    }
    private lateinit var preferences: SharedPreferences
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        preferences = activity!!.getSharedPreferences(FILE_SETTINGS, Context.MODE_PRIVATE)
        nightMode.isChecked = preferences.getBoolean(KEY_NIGHT_MODE, nightMode.isChecked)
        super.onViewCreated(view, savedInstanceState)
        nightMode.setOnCheckedChangeListener { _, isChecked ->
            preferences.edit().putBoolean("open",true).apply()
            preferences.edit().putBoolean(KEY_NIGHT_MODE, isChecked).apply()
            if (isChecked) AppCompatDelegate.setDefaultNightMode(DAY_NIGHT)
            else AppCompatDelegate.setDefaultNightMode(DAY_LIGHT)
        }
    }
}