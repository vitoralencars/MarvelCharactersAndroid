package com.vitor.myapplication.util

import androidx.lifecycle.MutableLiveData

object ServiceHelper {

    val notConnected = MutableLiveData<Boolean>()
    val generalError = MutableLiveData<Boolean>()

}