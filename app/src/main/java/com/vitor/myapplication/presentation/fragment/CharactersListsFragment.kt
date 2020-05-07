package com.vitor.myapplication.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sothree.slidinguppanel.SlidingUpPanelLayout

import com.vitor.myapplication.R
import com.vitor.myapplication.databinding.FragmentCharactersListsBinding
import com.vitor.myapplication.model.Character
import com.vitor.myapplication.presentation.adapter.ViewPagerAdapter
import com.vitor.myapplication.util.listener.SelectedCharacterListener
import com.vitor.myapplication.viewmodel.FavoriteCharactersViewModel
import kotlinx.android.synthetic.main.fragment_characters_lists.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class CharactersListsFragment : BaseFragment(), BottomNavigationView.OnNavigationItemSelectedListener,
    SelectedCharacterListener {

    private lateinit var binding: FragmentCharactersListsBinding

    private val favoriteCharactersViewModel by inject<FavoriteCharactersViewModel>()

    private val charactersServiceListFragment = CharactersServiceListFragment()
    private val favoritesFragment = FavoriteCharactersFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_characters_lists,
            container,
            false
        )

        binding.apply {
            binding.lifecycleOwner = viewLifecycleOwner
            favoriteCharactersViewModel = this@CharactersListsFragment.favoriteCharactersViewModel
        }

        charactersServiceListFragment.setSelectedCharactedListener(this)
        favoritesFragment.setSelectedCharactedListener(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeFavoriteCharacters()
        favoriteCharactersViewModel.fetchFavoriteCharacters()
    }

    private fun observeFavoriteCharacters(){
        favoriteCharactersViewModel.favoritesLiveData().observe(viewLifecycleOwner, Observer {
            setUpViewPager()
            bnv_main.setOnNavigationItemSelectedListener(this)
        })
    }

    private fun setUpViewPager(){
        vp_fragments.adapter = ViewPagerAdapter(
            listOf(charactersServiceListFragment, favoritesFragment),
            childFragmentManager,
            lifecycle
        )

        vp_fragments.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                when(position){
                    0 -> bnv_main.selectedItemId = R.id.bottom_nav_characters_list
                    1 -> bnv_main.selectedItemId = R.id.bottom_nav_favorites
                }
            }
        })

        handleBackPressed(getBackPressedCallback())
    }

    private fun checkPanelState(){
        if(panel_layout.panelState == SlidingUpPanelLayout.PanelState.COLLAPSED){
            panel_layout.panelState = SlidingUpPanelLayout.PanelState.ANCHORED
        }else{
            panel_layout.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.bottom_nav_characters_list -> vp_fragments.currentItem = 0
            R.id.bottom_nav_favorites -> vp_fragments.currentItem = 1
        }

        return true
    }

    override fun onCharacterSelected(character: Character) {
        binding.selectedCharacter = character
        checkPanelState()
    }

    private fun getBackPressedCallback() = object: OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            if(panel_layout.panelState == SlidingUpPanelLayout.PanelState.ANCHORED){
                panel_layout.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            }else{
                if(vp_fragments.currentItem == 1){
                    vp_fragments.currentItem = 0
                }else{
                    requireActivity().finish()
                }
            }
        }

    }

}
