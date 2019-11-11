package com.benjaminledet.pokedex.ui.item

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.benjaminledet.pokedex.data.model.ItemCategory
import com.benjaminledet.pokedex.data.model.ItemPocket
import com.benjaminledet.pokedex.data.repository.ItemRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ItemsViewModel(application: Application): AndroidViewModel(application), KoinComponent {

    private val itemRepository by inject<ItemRepository>()

    val itemPocketListing = itemRepository.getAllItemPocketsPagedList(viewModelScope, 7)

    fun itemCategoryListing(itemPocket: ItemPocket) = itemRepository.getAllItemCategoriesPagedList(viewModelScope, 6, itemPocket)

    fun itemListing(itemCategory: ItemCategory) = itemRepository.getAllItemsPagedList(viewModelScope, 8, itemCategory)

}