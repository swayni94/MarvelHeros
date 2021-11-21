package com.example.marvel.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.data.model.Comic
import com.example.marvel.databinding.ItemCharacterComicBinding
import com.squareup.picasso.Picasso

class ComicsRecyclerViewAdapter : RecyclerView.Adapter<ComicsRecyclerViewAdapter.ViewHolder>() {

    private val comicArrayList = ArrayList<Comic>()
    inner class ViewHolder(private val binding: ItemCharacterComicBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(comic: Comic){
            val path = "${comic.thumbnail.path.replace("http://","https://")}/standard_amazing.${comic.thumbnail.extension}"
            Picasso.get().load(path).fit().into(binding.image)
            binding.nameText.text = comic.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCharacterComicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comicArrayList[position])
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.left_to_right)
    }

    override fun getItemCount(): Int {
        return comicArrayList.size
    }

    fun addComicList(comics: List<Comic>){
        comicArrayList.addAll(comics)
        notifyItemInserted(comicArrayList.size)
    }
}