package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class PlanetAdapter : ListAdapter<Planet, PlanetAdapter.PlanetViewHolder>(PlanetDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.item_planet, parent, false)
        return PlanetViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PlanetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val planetName: TextView = view.findViewById(R.id.name)

        fun bind(item: Planet) {
            planetName.text = item.name
        }
    }
}

private class PlanetDiffUtil : DiffUtil.ItemCallback<Planet>() {
    override fun areItemsTheSame(oldItem: Planet, newItem: Planet): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Planet, newItem: Planet): Boolean {
        return oldItem == newItem
    }
}