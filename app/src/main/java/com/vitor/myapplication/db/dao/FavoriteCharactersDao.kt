package com.vitor.myapplication.db.dao

import androidx.room.*
import com.vitor.myapplication.db.entity.CharacterEntity
import com.vitor.myapplication.util.constant.DatabaseConstants

@Dao
interface FavoriteCharactersDao {

    @Query(DatabaseConstants.SELECT_FAVORITES_CHARACTERS_QUERY)
    suspend fun fetchFavoriteCharacters(): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(vararg character: CharacterEntity)

    @Delete
    suspend fun deleteCharacter(vararg character: CharacterEntity)

}