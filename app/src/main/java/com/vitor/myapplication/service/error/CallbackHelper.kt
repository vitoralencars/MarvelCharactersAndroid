package com.vitor.myapplication.service.error

import com.vitor.myapplication.util.ServiceHelper

abstract class CallbackHelper : ApiResponseErrorHandler {

    override fun onTimeOutError() {
        ServiceHelper.generalError.postValue(true)
    }

    override fun onRequestError() {
        ServiceHelper.generalError.postValue(true)
    }

    override fun onServerError() {
        ServiceHelper.generalError.postValue(true)
    }

    override fun onNotConnectedError() {
        ServiceHelper.notConnected.postValue(true)
    }

}