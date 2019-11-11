package com.benjaminledet.pokedex.data.repository

import android.util.Log
import androidx.paging.toLiveData
import com.benjaminledet.pokedex.data.local.dao.ItemCategoryDao
import com.benjaminledet.pokedex.data.local.dao.ItemDao
import com.benjaminledet.pokedex.data.local.dao.ItemPocketDao
import com.benjaminledet.pokedex.data.model.Item
import com.benjaminledet.pokedex.data.model.ItemCategory
import com.benjaminledet.pokedex.data.model.ItemPocket
import com.benjaminledet.pokedex.data.remote.PokeApiClient
import com.benjaminledet.pokedex.data.repository.utils.BoundaryCallback
import com.benjaminledet.pokedex.data.repository.utils.Listing
import com.benjaminledet.pokedex.extensions.indexOrMax
import kotlinx.coroutines.CoroutineScope
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ItemRepository: KoinComponent {

    private val pokeApiClient by inject<PokeApiClient>()

    private val itemDao by inject<ItemDao>()
    private val itemCategoryDao by inject<ItemCategoryDao>()
    private val itemPocketDao by inject<ItemPocketDao>()

    fun getAllItemPocketsPagedList(scope: CoroutineScope, pageSize: Int): Listing<ItemPocket> {

        // create a boundary callback which will observe when the user reaches to the edges of
        // the list and update the database with extra data.
        val boundaryCallback = BoundaryCallback<ItemPocket>(
            scope = scope,
            loadMore = { offset ->
                val response = pokeApiClient.getItemPocketList(offset)
                insertItemPockets(response)
            },
            getOffset = { itemPocket -> itemPocket.id }
        )

        val pagedList = itemPocketDao.getAllPaged().toLiveData(
            pageSize = pageSize,
            boundaryCallback = boundaryCallback
        )

        return Listing(
            pagedList = pagedList,
            networkState = boundaryCallback.networkState,
            refresh = null,
            refreshState = null
        )
    }

    fun getAllItemCategoriesPagedList(scope: CoroutineScope, pageSize: Int, itemPocket: ItemPocket): Listing<ItemCategory> {

        // create a boundary callback which will observe when the user reaches to the edges of
        // the list and update the database with extra data.
        val boundaryCallback = BoundaryCallback<ItemCategory>(
            scope = scope,
            loadMore = { offset ->
                val response = pokeApiClient.getItemCategoryListWithItems(
                    idList = itemPocket.itemCategoryNameList.run { subList(indexOrMax(offset), indexOrMax(offset + pageSize)) } ,
                    itemPocketId = itemPocket.id
                )
                insertItemCategories(response.keys.toList())
                insertItems(response.values.flatten())
            },
            getOffset = { itemCategory -> itemPocket.itemCategoryNameList.indexOf(itemCategory.name.toLowerCase()) + 1 }
        )


        val pagedList = itemCategoryDao.getAllPagedOfPocket(itemPocket.id).toLiveData(
            pageSize = pageSize,
            boundaryCallback = boundaryCallback
        )

        return Listing(
            pagedList = pagedList,
            networkState = boundaryCallback.networkState,
            refresh = null,
            refreshState = null
        )
    }

    fun getAllItemsPagedList(scope: CoroutineScope, pageSize: Int, itemCategory: ItemCategory): Listing<Item> {

        // create a boundary callback which will observe when the user reaches to the edges of
        // the list and update the database with extra data.
        val boundaryCallback = BoundaryCallback<Item>(
            scope = scope,
            loadMore = {
                // nothing to do as all items are loaded with their category
            },
            getOffset = { item -> item.id }
        )

        val pagedList = itemDao.getAllPagedOfCategory(itemCategory.id).toLiveData(
            pageSize = pageSize,
            boundaryCallback = boundaryCallback
        )

        return Listing(
            pagedList = pagedList,
            networkState = boundaryCallback.networkState,
            refresh = null,
            refreshState = null
        )
    }

    private suspend fun insertItemPockets(itemPockets: List<ItemPocket>) {
        Log.v(TAG, "insert itemPockets: $itemPockets")
        itemPocketDao.insert(itemPockets)
    }

    private suspend fun insertItemCategories(itemCategories: List<ItemCategory>) {
        Log.v(TAG, "insert itemCategories: $itemCategories")
        itemCategoryDao.insert(itemCategories)
    }

    private suspend fun insertItems(items: List<Item>) {
        Log.v(TAG, "insert items: $items")
        itemDao.insert(items)
    }

    companion object {
        private const val TAG = "ItemRepository"
    }
}