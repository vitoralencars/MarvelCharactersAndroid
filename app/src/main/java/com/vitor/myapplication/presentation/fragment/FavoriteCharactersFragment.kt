package com.vitor.myapplication.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.vitor.myapplication.R
import com.vitor.myapplication.databinding.FragmentFavoriteCharactersBinding
import com.vitor.myapplication.model.Character
import com.vitor.myapplication.presentation.adapter.FavoritesAdapter
import com.vitor.myapplication.util.listener.RecyclerViewOnClickListener
import com.vitor.myapplication.util.listener.SelectedCharacterListener
import com.vitor.myapplication.viewmodel.FavoriteCharactersViewModel
import kotlinx.android.synthetic.main.fragment_favorite_characters.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteCharactersFragment : BaseFragment(), RecyclerViewOnClickListener {

    private lateinit var selectedCharacterListener: SelectedCharacterListener

    private lateinit var binding: FragmentFavoriteCharactersBinding

    private val listAdapter = FavoritesAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_favorite_characters,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewsActions()
        observeStaticFavoritesList()
    }

    override fun onResume() {
        super.onResume()
        hideKeyboard()
    }

    private fun setUpViewsActions(){
        rv_favorites.adapter = listAdapter
    }

    private fun observeStaticFavoritesList(){
        FavoriteCharactersViewModel.staticFavorites.observe(viewLifecycleOwner, Observer {
            listAdapter.updateList(it)
        })
    }

    fun setSelectedCharactedListener(selectedCharacterListener: SelectedCharacterListener){
        this.selectedCharacterListener = selectedCharacterListener
    }

    override fun onItemClick(item: Any) {
        selectedCharacterListener.onCharacterSelected(item as Character)
    }

}
