package com.vitor.myapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.vitor.myapplication.R
import com.vitor.myapplication.databinding.ItemCharacterServiceBinding
import com.vitor.myapplication.model.Character
import com.vitor.myapplication.util.listener.RecyclerViewOnClickListener
import com.vitor.myapplication.viewmodel.FavoriteCharactersViewModel

class CharactersServiceAdapter(private val clickListener: RecyclerViewOnClickListener, private val favoritesViewModel: FavoriteCharactersViewModel, private val lifecycleOwner: LifecycleOwner):
    RecyclerView.Adapter<CharactersServiceAdapter.ViewHolder>() {

    private val characters = ArrayList<Character>()

    fun resetList() = characters.clear()

    fun updateList(newList: List<Character>){
        characters.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_character_service,
            parent,
            false
        ))

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(characters[position])

    inner class ViewHolder(var binding: ItemCharacterServiceBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        fun bind(item: Character){

            binding.apply {
                character = item
                favoritesViewModel = this@CharactersServiceAdapter.favoritesViewModel
                lifecycleOwner = this@CharactersServiceAdapter.lifecycleOwner
            }

            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            clickListener.onItemClick(characters[adapterPosition])
        }
    }
}