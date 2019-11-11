package com.benjaminledet.pokedex.manager

import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val managerModule = module {

    single { PreferencesManager(androidApplication()) }

}
