package com.vitor.myapplication.util

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object RequestsHelper {

    fun getTimestamp() = System.currentTimeMillis()

    fun getMd5Hash(timestamp: Long, privateKey: String, publicKey: String): String?{
        return try{
            val data = timestamp.toString() + privateKey + publicKey
            val md = MessageDigest.getInstance("MD5")

            BigInteger(1, md.digest(data.toByteArray()))
                .toString(16)
                .padStart(32, '0')
        }catch (e: NoSuchAlgorithmException){
            null
        }
    }

}