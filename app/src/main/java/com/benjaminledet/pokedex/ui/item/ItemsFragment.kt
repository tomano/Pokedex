package com.benjaminledet.pokedex.ui.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.benjaminledet.pokedex.R
import com.benjaminledet.pokedex.ui.item.adapter.ItemPocketPagedListAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_pokemons_container.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemsFragment: Fragment() {

    private val viewModel by viewModel<ItemsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPagerAndTabLayout()
    }

    private fun setupViewPagerAndTabLayout() {
        val adapter = ItemPocketPagedListAdapter(viewModel, viewLifecycleOwner).apply {
            viewModel.itemPocketListing.pagedList.observe(viewLifecycleOwner, Observer {
                submitList(it)
            })
        }

        viewPager.adapter = adapter
        viewPager.isUserInputEnabled = false
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = viewModel.itemPocketListing.pagedList.value?.snapshot()?.get(position)?.name
        }.attach()
    }
}