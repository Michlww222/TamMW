package com.android.wasik.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.wasik.data.Starship
import com.android.wasik.databinding.ItemLayoutBinding

class StarshipAdapter(private val starships: List<Starship>, private val onItemClick: (Starship) -> Unit) :
    RecyclerView.Adapter<StarshipAdapter.StarshipViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarshipViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StarshipViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StarshipViewHolder, position: Int) {
        val starship = starships[position]
        holder.bind(starship)
    }

    override fun getItemCount(): Int = starships.size

    inner class StarshipViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick(starships[adapterPosition])
            }
        }

        fun bind(starship: Starship) {
            binding.itemName.text = starship.name
            // You can bind other properties of the starship here if needed
        }
    }
}
