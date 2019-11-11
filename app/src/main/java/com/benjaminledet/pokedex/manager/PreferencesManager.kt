package com.benjaminledet.pokedex.manager

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import org.koin.standalone.KoinComponent

class PreferencesManager(application: Application): KoinComponent {

    private var preferences = PreferenceManager.getDefaultSharedPreferences(application)

    var nightMode: Int
        get() = preferences.getInt(NIGHT_MODE, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        set(value) {
            preferences.edit { putInt(NIGHT_MODE, value) }
            AppCompatDelegate.setDefaultNightMode(value)
        }

    companion object {
        private const val NIGHT_MODE = "NIGHT_MODE"
    }
}