package com.vitor.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vitor.myapplication.db.dao.FavoriteCharactersDao
import com.vitor.myapplication.db.entity.CharacterEntity

@Database(version = 1, entities = [CharacterEntity::class], exportSchema = false)
abstract class FavoriteCharactersDatabase : RoomDatabase(){
    abstract fun favoriteCharactersDao(): FavoriteCharactersDao
}