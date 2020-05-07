package com.vitor.myapplication.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.vitor.myapplication.R

object GlideHelper {

    @JvmStatic
    @BindingAdapter("characterPicture")
    fun loadCharacterPicture(view: ImageView, url: String){
        Glide.with(view.context)
            .load(url)
            .error(R.drawable.ic_characters_outline)
            .fitCenter()
            .into(view)
    }

}