package com.benjaminledet.pokedex.ui

import com.benjaminledet.pokedex.ui.account.AccountViewModel
import com.benjaminledet.pokedex.ui.item.ItemsViewModel
import com.benjaminledet.pokedex.ui.main.MainViewModel
import com.benjaminledet.pokedex.ui.pokemon.detail.PokemonDetailViewModel
import com.benjaminledet.pokedex.ui.pokemon.list.PokemonsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val uiModule = module {

    viewModel { AccountViewModel(androidApplication()) }

    viewModel { ItemsViewModel(androidApplication()) }

    viewModel { MainViewModel(androidApplication()) }

    viewModel { PokemonsViewModel(androidApplication()) }

    viewModel { PokemonDetailViewModel(androidApplication()) }

}