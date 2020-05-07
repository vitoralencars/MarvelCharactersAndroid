package com.vitor.myapplication.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.vitor.myapplication.model.Character
import com.vitor.myapplication.model.Participations
import com.vitor.myapplication.model.Thumbnail
import com.vitor.myapplication.util.constant.DatabaseConstants

@Entity(tableName = DatabaseConstants.CHARACTER_TABLE_NAME)
data class CharacterEntity (
    @PrimaryKey
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("picture_path")
    val picturePath: String,
    @SerializedName("picture_extension")
    val pictureExtension: String,
    @SerializedName("comics")
    val comics: Int,
    @SerializedName("stories")
    val stories: Int,
    @SerializedName("series")
    val series: Int,
    @SerializedName("events")
    val events: Int
){
    companion object{
        fun fromCharacter(character: Character) = CharacterEntity(
            character.id,
            character.name,
            character.description,
            character.thumbnail.path,
            character.thumbnail.extension,
            character.comics.available,
            character.stories.available,
            character.series.available,
            character.events.available
        )
    }

    fun toCharacter() = Character(
        id,
        name,
        description,
        Thumbnail(picturePath, pictureExtension),
        Participations(comics),
        Participations(stories),
        Participations(series),
        Participations(events)
    )
}