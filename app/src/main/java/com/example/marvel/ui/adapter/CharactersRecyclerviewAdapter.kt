package com.example.marvel.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.data.model.Character
import com.example.marvel.databinding.ItemCharacterComicBinding
import com.squareup.picasso.Picasso

class CharactersRecyclerviewAdapter(private val listener: CharacterListener) : RecyclerView.Adapter<CharactersRecyclerviewAdapter.ViewHolder>()  {

    private val characterArrayList = ArrayList<Character>()

    inner class ViewHolder(private val binding : ItemCharacterComicBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(character: Character){
            val imagePath = "${character.thumbnail.path.replace("http://","https://")}/standard_amazing.${character.thumbnail.extension}"
            Picasso.get().load(imagePath).resize(150, 150).centerCrop().into(binding.image)
            println(imagePath)
            binding.nameText.text = character.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCharacterComicBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characterArrayList[position])
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.left_to_right)
        holder.itemView.setOnClickListener {
            listener.openCharacterDetailPage(characterArrayList[position])
        }
    }

    override fun getItemCount(): Int {
        return characterArrayList.size
    }

    fun addList(characters: List<Character>){
        characterArrayList.addAll(characters)
        notifyItemInserted(characterArrayList.size)
    }

    fun getLastItem(): Int{
        return characterArrayList.lastIndex
    }


    interface CharacterListener{
        fun openCharacterDetailPage(character: Character)
    }
}