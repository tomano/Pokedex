package com.benjaminledet.pokedex.ui.pokemon.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.benjaminledet.pokedex.R
import com.benjaminledet.pokedex.data.repository.utils.Status
import com.benjaminledet.pokedex.extensions.intentFor
import com.benjaminledet.pokedex.extensions.longToast
import com.benjaminledet.pokedex.ui.pokemon.detail.PokemonDetailActivity
import com.benjaminledet.pokedex.ui.pokemon.list.adapter.PokemonListAdapter
import com.benjaminledet.pokedex.ui.pokemon.list.adapter.PokemonPagedListAdapter
import kotlinx.android.synthetic.main.fragment_pokemons_content.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonsContentFragment: Fragment() {

    private val viewModel by viewModel<PokemonsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pokemons_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerViewAdapter(arguments?.getBoolean("pagination", false) ?: false)
        setupNetworkState()
        setupRefreshState()
    }

    private fun setupRecyclerViewAdapter(pagination: Boolean) {
        if (pagination) {

            val adapter = PokemonPagedListAdapter().apply {
                viewModel.pokemons.observe(viewLifecycleOwner, Observer { list ->
                    submitList(list)
                })

                onClick = { pokemon ->
                    parentFragment?.findNavController()?.navigate(
                        R.id.pokemonContainer_to_pokemonDetail,
                        bundleOf("pokemonId" to pokemon.id)
                    )
                }
            }
            recyclerView.adapter = adapter

        } else {

            val adapter = PokemonListAdapter().apply {
                viewModel.pokemonListNoPaginated.observe(viewLifecycleOwner, Observer {
                    submitList(it)
                })

                onClick = { pokemon ->
                    requireActivity().startActivity(requireContext().intentFor<PokemonDetailActivity>("pokemonId" to pokemon.id))
                }
            }
            recyclerView.adapter = adapter
        }

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
    }

    private fun setupNetworkState() {
        viewModel.networkState.observe(viewLifecycleOwner, Observer { networkState ->
            if (networkState.status == Status.FAILED) {
                requireContext().longToast(getString(R.string.error_load))
            }
        })
    }

    private fun setupRefreshState() {
        viewModel.refreshState?.observe(viewLifecycleOwner, Observer { refreshState ->
            swipeRefreshLayout.isRefreshing = refreshState.status == Status.RUNNING

            if (refreshState.status == Status.FAILED) {
                requireContext().longToast(getString(R.string.error_refresh))
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }
}