package com.benjaminledet.pokedex.extensions

/**
 * Get the max index of the list if the index given is bigger than the size list
 * Prevent from getting an exception because the index is bigger t
 */
fun <T>List<T>.indexOrMax(index: Int): Int = if (index > size) size else index