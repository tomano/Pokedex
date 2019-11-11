package com.benjaminledet.pokedex

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.benjaminledet.pokedex.manager.PreferencesManager
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin
import org.koin.log.EmptyLogger

class App: Application() {

    private val preferencesManager by inject<PreferencesManager>()

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModule, logger = EmptyLogger())
        AppCompatDelegate.setDefaultNightMode(preferencesManager.nightMode)
    }
}