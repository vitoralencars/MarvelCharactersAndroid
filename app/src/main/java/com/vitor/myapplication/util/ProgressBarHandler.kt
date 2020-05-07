package com.vitor.myapplication.util

import android.view.View
import androidx.lifecycle.MutableLiveData

object ProgressBarHandler {

    val loading = MutableLiveData<Int>()

    fun showProgressBar(){
        loading.postValue(View.VISIBLE)
    }

    fun hideProgressBar(){
        loading.postValue(View.GONE)
    }

}