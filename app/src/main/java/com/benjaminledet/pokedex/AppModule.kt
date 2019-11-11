package com.benjaminledet.pokedex

import com.benjaminledet.pokedex.data.dataModule
import com.benjaminledet.pokedex.manager.managerModule
import com.benjaminledet.pokedex.ui.uiModule

val appModule = listOf(dataModule, managerModule, uiModule)