package com.benjaminledet.pokedex.ui.pokemon.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import com.benjaminledet.pokedex.data.model.Pokemon
import com.benjaminledet.pokedex.data.repository.PokemonRepository
import com.benjaminledet.pokedex.data.repository.utils.NetworkState
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class PokemonsViewModel(application: Application): AndroidViewModel(application), KoinComponent {

    private val repository by inject<PokemonRepository>()

    private val pokemonListing = repository.getAllPokemonsPagedList(viewModelScope, 30)

    val pokemons: LiveData<PagedList<Pokemon>> = pokemonListing.pagedList

    val networkState: LiveData<NetworkState> = pokemonListing.networkState
    val refreshState: LiveData<NetworkState>? = pokemonListing.refreshState

    val pokemonListNoPaginated = repository.getAllPokemonsObservable()

    fun refresh() {
        pokemonListing.refresh?.invoke()
    }
}