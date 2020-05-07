package com.vitor.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vitor.myapplication.model.Character
import com.vitor.myapplication.usecase.database.FavoriteCharactersUseCases
import com.vitor.myapplication.util.ProgressBarHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteCharactersViewModel(private val useCases: FavoriteCharactersUseCases): BaseViewModel() {

    private val favoritesList = MutableLiveData<List<Character>>()
    fun favoritesLiveData(): LiveData<List<Character>> = favoritesList

    fun fetchFavoriteCharacters(){
        launch {
            ProgressBarHandler.showProgressBar()

            withContext(coroutineContext) {
                favoritesList.value = useCases.fetchFavoriteCharacters()
            }

            setFavoriteCharacters(favoritesList.value)

            ProgressBarHandler.hideProgressBar()
        }
    }

    fun insertFavoriteCharacter(character: Character){
        launch {
            withContext(coroutineContext) {
                useCases.insertFavoriteCharacter(character)
            }

            addCharacter(character)
        }
    }

    fun deleteFavoriteCharacter(character: Character){
        launch {
            withContext(coroutineContext) {
                useCases.deleteFavoriteCharacter(character)
            }

            removeCharacter(character)
        }
    }

    fun onFavoriteChecked(checked: Boolean, character: Character){
        if(checked){
            insertFavoriteCharacter(character)
        }else{
            deleteFavoriteCharacter(character)
        }
    }

    companion object{
        @JvmStatic
        val staticFavorites = MutableLiveData<List<Character>>(emptyList())

        private val auxList = arrayListOf<Character>()

        private fun setFavoriteCharacters(characaters: List<Character>?){
            characaters?.let {
                auxList.apply {
                    clear()
                    addAll(characaters)
                }
                updateFavoritesList()
            }
        }

        private fun addCharacter(character: Character){
            if(!auxList.contains(character)) {
                auxList.add(character)
                updateFavoritesList()
            }
        }

        private fun removeCharacter(character: Character){
            auxList.remove(character)
            updateFavoritesList()
        }

        private fun updateFavoritesList(){
            staticFavorites.value = auxList
        }
    }

}