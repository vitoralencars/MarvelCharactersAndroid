package com.vitor.myapplication.util.listener

import com.vitor.myapplication.model.Character

interface SelectedCharacterListener {

    fun onCharacterSelected(character: Character)

}