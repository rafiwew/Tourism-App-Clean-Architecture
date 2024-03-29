package com.piwew.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.piwew.core.databinding.ItemListTourismBinding
import com.piwew.core.domain.model.Tourism
import com.piwew.core.utils.loadImage

class TourismAdapter : ListAdapter<Tourism, TourismAdapter.ListViewHolder>(TourismDiffCallback()) {

    var onItemClick: ((Tourism) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemListTourismBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(private val binding: ItemListTourismBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Tourism) {
            with(binding) {
                ivItemImage.loadImage(data.image)
                tvItemTitle.text = data.name
                tvItemSubtitle.text = data.address
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }
    }
}

class TourismDiffCallback : DiffUtil.ItemCallback<Tourism>() {
    override fun areItemsTheSame(oldItem: Tourism, newItem: Tourism): Boolean {
        return oldItem.tourismId == newItem.tourismId
    }

    override fun areContentsTheSame(oldItem: Tourism, newItem: Tourism): Boolean {
        return oldItem == newItem
    }
}
