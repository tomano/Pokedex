package com.benjaminledet.pokedex.ui.item.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.benjaminledet.pokedex.R
import com.benjaminledet.pokedex.data.model.ItemCategory
import com.benjaminledet.pokedex.ui.item.ItemsViewModel
import kotlinx.android.synthetic.main.item_category.view.*

class ItemCategoryPagedListAdapter(private val viewModel: ItemsViewModel, private val lifecycleOwner: LifecycleOwner): PagedListAdapter<ItemCategory, ItemCategoryPagedListAdapter.ItemCategoryViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCategoryViewHolder {
        return ItemCategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))
    }

    override fun onBindViewHolder(holder: ItemCategoryViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ItemCategoryViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(itemCategory: ItemCategory) {
            itemView.name.text = itemCategory.name

            val adapter = ItemPagedListAdapter().apply {
                viewModel.itemListing(itemCategory).pagedList.observe(lifecycleOwner, Observer {
                    submitList(it)
                })
            }
            itemView.recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            itemView.recyclerView.adapter = adapter
        }
    }

    companion object {

        val DIFF_UTIL = object: DiffUtil.ItemCallback<ItemCategory>() {
            override fun areItemsTheSame(oldItem: ItemCategory, newItem: ItemCategory): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemCategory, newItem: ItemCategory): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}