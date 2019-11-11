package com.benjaminledet.pokedex.ui.pokemon.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.benjaminledet.pokedex.R
import com.benjaminledet.pokedex.extensions.instanceOf
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_pokemons_container.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonsContainerFragment: Fragment() {

    private val viewModel by viewModel<PokemonsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pokemons_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPagerAndTabLayout()
    }

    private fun setupViewPagerAndTabLayout() {
        val viewPagerAdapter = object: FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 2

            override fun createFragment(position: Int): Fragment = instanceOf<PokemonsContentFragment>("pagination" to (position == 0))
        }

        viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getString(if (position == 0) R.string.paginated else R.string.no_paginated)
        }.attach()
    }
}