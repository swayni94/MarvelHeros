package com.example.marvel.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.data.model.CharacterModel
import com.example.marvel.databinding.ItemCharacterComicBinding
import com.squareup.picasso.Picasso

class FavoriteRecyclerViewAdapter : RecyclerView.Adapter<FavoriteRecyclerViewAdapter.ViewHolder>() {

    private val characterArrayList = ArrayList<CharacterModel>()

    inner class ViewHolder(private val binding : ItemCharacterComicBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(characterModel: CharacterModel){
            Picasso.get().load(characterModel.imagePath).into(binding.image)
            binding.nameText.text = characterModel.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCharacterComicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characterArrayList[position])
    }

    override fun getItemCount(): Int {
        return characterArrayList.size
    }


    fun addList(characters : List<CharacterModel>){
        notifyItemRangeChanged(0, characterArrayList.size)
        characterArrayList.clear()
        characterArrayList.addAll(characters)
        notifyItemInserted(characterArrayList.size)
    }
}