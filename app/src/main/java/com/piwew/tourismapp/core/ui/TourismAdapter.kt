package com.piwew.tourismapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.piwew.tourismapp.core.data.source.local.entity.TourismEntity
import com.piwew.tourismapp.core.utils.loadImage
import com.piwew.tourismapp.databinding.ItemListTourismBinding

class TourismAdapter : ListAdapter<TourismEntity, TourismAdapter.ListViewHolder>(TourismDiffCallback()) {

    var onItemClick: ((TourismEntity) -> Unit)? = null

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
        fun bind(data: TourismEntity) {
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

class TourismDiffCallback : DiffUtil.ItemCallback<TourismEntity>() {
    override fun areItemsTheSame(oldItem: TourismEntity, newItem: TourismEntity): Boolean {
        return oldItem.tourismId == newItem.tourismId
    }

    override fun areContentsTheSame(oldItem: TourismEntity, newItem: TourismEntity): Boolean {
        return oldItem == newItem
    }
}
