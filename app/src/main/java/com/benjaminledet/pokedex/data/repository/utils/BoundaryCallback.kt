package com.benjaminledet.pokedex.data.repository.utils

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.standalone.KoinComponent

/**
 * This boundary callback gets notified when user reaches to the edges of the list such that the
 * database cannot provide any more data.
 */
class BoundaryCallback<T>(

    private val scope: CoroutineScope,

    private val loadMore: suspend CoroutineScope.(Int) -> Unit,

    private val getOffset: (T) -> Int

): PagedList.BoundaryCallback<T>(), KoinComponent {

    val networkState = MutableLiveData<NetworkState>()

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    override fun onZeroItemsLoaded() {
        loadItems(0)
    }

    /**
     * User reached to the end of the list.
     */
    override fun onItemAtEndLoaded(itemAtEnd: T) {
        loadItems(getOffset(itemAtEnd))
    }

    private fun loadItems(offset: Int) {
        scope.launch {
            networkState.postValue(NetworkState.LOADING)
            try {
                loadMore(offset)
                networkState.postValue(NetworkState.LOADING)
            } catch (e: Exception) {
                networkState.postValue(NetworkState.error(e.message))
            }
        }
    }
}