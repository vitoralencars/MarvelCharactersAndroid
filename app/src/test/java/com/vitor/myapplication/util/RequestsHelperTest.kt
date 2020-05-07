package com.vitor.myapplication.util

import com.vitor.myapplication.util.constant.ServiceConstants
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*

class RequestsHelperTest {

    @Test
    fun `should return md5 hash with 32 characters`(){
        val md5Hash = RequestsHelper.getMd5Hash(
            RequestsHelper.getTimestamp(),
            ServiceConstants.PRIVATE_KEY,
            ServiceConstants.PUBLIC_KEY
        )

        assertEquals(md5Hash?.length, 32)
    }

    @Test
    fun `should return different md5 hashes to different timestamps`(){
        val timestamp1 = RequestsHelper.getTimestamp()

        runBlocking {
            delay(1000)
        }

        val timestamp2 = RequestsHelper.getTimestamp()

        val md5Hash1 = RequestsHelper.getMd5Hash(
            timestamp1,
            ServiceConstants.PRIVATE_KEY,
            ServiceConstants.PUBLIC_KEY
        )

        val md5Hash2 = RequestsHelper.getMd5Hash(
            timestamp2,
            ServiceConstants.PRIVATE_KEY,
            ServiceConstants.PUBLIC_KEY
        )

        assertNotNull(md5Hash1)
        assertNotNull(md5Hash2)
        assertNotEquals(md5Hash1, md5Hash2)
    }

    @Test
    fun `should return same md5 hashes to same timestamps`(){
        val timestamp = RequestsHelper.getTimestamp()

        val md5Hash1 = RequestsHelper.getMd5Hash(
            timestamp,
            ServiceConstants.PRIVATE_KEY,
            ServiceConstants.PUBLIC_KEY
        )

        val md5Hash2 = RequestsHelper.getMd5Hash(
            timestamp,
            ServiceConstants.PRIVATE_KEY,
            ServiceConstants.PUBLIC_KEY
        )

        assertNotNull(md5Hash1)
        assertEquals(md5Hash1, md5Hash2)
    }

}