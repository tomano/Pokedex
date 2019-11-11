package com.benjaminledet.pokedex.ui.pokemon.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.benjaminledet.pokedex.R
import com.benjaminledet.pokedex.data.model.Pokemon
import com.benjaminledet.pokedex.util.OnItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonListAdapter: ListAdapter<Pokemon, PokemonListAdapter.PokemonViewHolder>(DIFF_UTIL) {

    var onClick: ((item: Pokemon) -> Unit)? = null

    var onLongClick: ((item: Pokemon) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PokemonViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(pokemon: Pokemon) {
            itemView.name.text = pokemon.name
            Picasso.get().load(pokemon.iconUrl).into(itemView.icon)

            val listener = object: OnItemClickListener<Pokemon> {
                override fun onItemClick(item: Pokemon) {
                    onClick?.invoke(item)
                }

                override fun onItemLongClick(item: Pokemon): Boolean {
                    onLongClick?.invoke(item)
                    return true
                }
            }

            itemView.setOnClickListener { listener.onItemClick(pokemon) }
            itemView.setOnLongClickListener { listener.onItemLongClick(pokemon) }
        }
    }

    companion object {
        val DIFF_UTIL = object: DiffUtil.ItemCallback<Pokemon>() {
            override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}