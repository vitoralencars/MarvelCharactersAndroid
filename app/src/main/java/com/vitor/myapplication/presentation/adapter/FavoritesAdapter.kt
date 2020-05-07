package com.vitor.myapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vitor.myapplication.R
import com.vitor.myapplication.databinding.ItemCharacterFavoriteBinding
import com.vitor.myapplication.model.Character
import com.vitor.myapplication.util.listener.RecyclerViewOnClickListener

class FavoritesAdapter(private val clickListener: RecyclerViewOnClickListener) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>(){

    private val characters = ArrayList<Character>()

    fun updateList(newList: List<Character>){
        characters.clear()
        characters.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_character_favorite,
            parent,
            false
        ))

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(characters[position])

    inner class ViewHolder(var binding: ItemCharacterFavoriteBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        fun bind(item: Character){
            binding.character = item

            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            clickListener.onItemClick(characters[adapterPosition])
        }

    }
}