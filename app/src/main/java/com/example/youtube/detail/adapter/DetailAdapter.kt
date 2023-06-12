package com.example.youtube.detail.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.youtube.data.remote.model.PLaylistItem
import com.example.youtube.data.remote.model.Playlists
import com.example.youtube.databinding.ItemDetailsBinding
import com.example.youtube.utils.loadImage

class DetailAdapter() : Adapter<DetailAdapter.DetailViewHolder>() {

    private var list = ArrayList<PLaylistItem.Item>()


    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<Playlists.Item>) {
        this.list = list as ArrayList<PLaylistItem.Item>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            ItemDetailsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class DetailViewHolder(private val binding: ItemDetailsBinding) :
        ViewHolder(binding.root) {
        fun bind(item: PLaylistItem.Item) {
            with(binding) {
                tvVideoName.text = item.snippet?.title
                tvTime.text = item.snippet?.publishedAt
                ivVideo.loadImage(item.snippet?.thumbnails?.standard?.url!!)
            }
        }
    }
}