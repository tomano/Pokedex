package com.benjaminledet.pokedex.ui.item.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.benjaminledet.pokedex.R
import com.benjaminledet.pokedex.data.model.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_item.view.*

class ItemPagedListAdapter: PagedListAdapter<Item, ItemPagedListAdapter.ItemViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(item: Item) {
            itemView.name.text = item.name
            Picasso.get()
                .load(item.iconUrl)
                .into(itemView.icon)
        }
    }

    companion object {
        val DIFF_UTIL = object: DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}