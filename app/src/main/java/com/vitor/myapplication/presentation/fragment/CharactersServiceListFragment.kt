package com.vitor.myapplication.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

import com.vitor.myapplication.R
import com.vitor.myapplication.databinding.FragmentCharactersServiceListBinding
import com.vitor.myapplication.model.Character
import com.vitor.myapplication.presentation.adapter.CharactersServiceAdapter
import com.vitor.myapplication.util.ProgressBarHandler
import com.vitor.myapplication.util.listener.RecyclerViewOnClickListener
import com.vitor.myapplication.util.listener.SelectedCharacterListener
import com.vitor.myapplication.util.extension.getString
import com.vitor.myapplication.viewmodel.FavoriteCharactersViewModel
import com.vitor.myapplication.viewmodel.ServiceCharactersViewModel
import kotlinx.android.synthetic.main.fragment_characters_service_list.*
import kotlinx.android.synthetic.main.view_not_connected.*
import kotlinx.android.synthetic.main.view_service_error_general.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class CharactersServiceListFragment : BaseFragment(), RecyclerViewOnClickListener {

    private lateinit var selectedCharacterListener: SelectedCharacterListener

    private lateinit var binding: FragmentCharactersServiceListBinding

    private val serviceCharactersViewModel by inject<ServiceCharactersViewModel>()
    private val favoriteCharactersViewModel by inject<FavoriteCharactersViewModel>()

    private lateinit var listAdapter: CharactersServiceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_characters_service_list,
            container,
            false
        )

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = serviceCharactersViewModel
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpViewsActions()
        observeCharactersList()
        fetchAllCharacters(true)
    }

    override fun onResume() {
        super.onResume()
        if(et_search.getString().isNotEmpty()){
            et_search.requestFocus()
        }
    }

    private fun setUpRecyclerView(){
        listAdapter = CharactersServiceAdapter(
            this,
            favoriteCharactersViewModel,
            viewLifecycleOwner
        )

        rv_characters.adapter = listAdapter
        rv_characters.addOnScrollListener(setUpRecyclerViewScollListener())
    }

    private fun setUpViewsActions(){
        et_search.setOnFocusChangeListener { _, isFocused ->
            binding.searchEditTextFocused = isFocused
        }

        et_search.setOnEditorActionListener { _, _, _ ->
            fetchCharacters(true)
            hideKeyboard()
            true
        }

        iv_search_icon.setOnClickListener {
            et_search.requestFocus()
            showKeyboard()
        }

        iv_search_back_icon.setOnClickListener {
            clearSearchFieldFocus()
            fetchCharacters(true)
        }

        btn_try_again_connection.setOnClickListener { fetchCharacters(true) }
        btn_try_again_error.setOnClickListener { fetchCharacters(true) }
    }

    private fun observeCharactersList(){
        serviceCharactersViewModel.charactersLiveData().observe(viewLifecycleOwner, Observer {
            listAdapter.updateList(it)
        })
    }

    private fun setUpRecyclerViewScollListener() = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if(!recyclerView.canScrollVertically(1) && ProgressBarHandler.loading.value != View.VISIBLE){
                serviceCharactersViewModel.updateOffset()
                fetchCharacters(false)
            }
        }
    }

    private fun fetchCharacters(setInitialState: Boolean){
        val name = et_search.getString()
        if(name.isEmpty()){
            fetchAllCharacters(setInitialState)
        }else{
            fetchCharactersByName(setInitialState)
        }
    }

    private fun fetchAllCharacters(setInitialState: Boolean){
        if(setInitialState) setInitialState()
        clearSearchFieldFocus()
        serviceCharactersViewModel.fetchAllCharacters()
    }

    private fun fetchCharactersByName(setInitialState: Boolean){
        if(setInitialState) setInitialState()
        serviceCharactersViewModel.fetchCharactersByName(et_search.getString())
    }

    private fun setInitialState(){
        listAdapter.resetList()
        serviceCharactersViewModel.resetState()
    }

    private fun clearSearchFieldFocus(){
        et_search.apply {
            clearFocus()
            setText("")
        }
        hideKeyboard()
    }

    fun setSelectedCharactedListener(selectedCharacterListener: SelectedCharacterListener){
        this.selectedCharacterListener = selectedCharacterListener
    }

    override fun onItemClick(item: Any) {
        selectedCharacterListener.onCharacterSelected(item as Character)
    }

}
