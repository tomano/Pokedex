package com.benjaminledet.pokedex.util

interface OnItemClickListener<T> {

    fun onItemClick(item: T)

    fun onItemLongClick(item: T): Boolean

}