package com.benjaminledet.pokedex.ui.item.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.benjaminledet.pokedex.R
import com.benjaminledet.pokedex.data.model.ItemPocket
import com.benjaminledet.pokedex.ui.item.ItemsViewModel
import kotlinx.android.synthetic.main.item_pocket.view.*

class ItemPocketPagedListAdapter(private val viewModel: ItemsViewModel, private val lifecycleOwner: LifecycleOwner): PagedListAdapter<ItemPocket, ItemPocketPagedListAdapter.ItemPocketViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPocketViewHolder {
        return ItemPocketViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pocket, parent, false))
    }

    override fun onBindViewHolder(holder: ItemPocketViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ItemPocketViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(itemPocket: ItemPocket) {
            val adapter = ItemCategoryPagedListAdapter(viewModel, lifecycleOwner).apply {
                viewModel.itemCategoryListing(itemPocket).pagedList.observe(lifecycleOwner, Observer {
                    submitList(it)
                })
            }
            itemView.recyclerView.itemAnimator = null
            itemView.recyclerView.adapter = adapter
        }
    }

    companion object {

        val DIFF_UTIL = object: DiffUtil.ItemCallback<ItemPocket>() {
            override fun areItemsTheSame(oldItem: ItemPocket, newItem: ItemPocket): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemPocket, newItem: ItemPocket): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}