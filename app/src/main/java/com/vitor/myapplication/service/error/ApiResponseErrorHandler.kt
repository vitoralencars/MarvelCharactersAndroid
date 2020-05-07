package com.vitor.myapplication.service.error

interface ApiResponseErrorHandler {
    fun onTimeOutError()
    fun onRequestError()
    fun onServerError()
    fun onNotConnectedError()
}