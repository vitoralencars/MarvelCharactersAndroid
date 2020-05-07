package com.vitor.myapplication.util

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule

class ProgressBarHandlerTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val progressBarObserver = mockk<Observer<Int>>(relaxed = true)

    @Test
    fun `should set progress bar loading visibility value to visible`(){
        ProgressBarHandler.loading.observeForever(progressBarObserver)

        ProgressBarHandler.showProgressBar()

        assertNotNull(ProgressBarHandler.loading.value)
        assertNotEquals(ProgressBarHandler.loading.value, View.GONE)

        verify { progressBarObserver.onChanged(View.VISIBLE) }
    }

    @Test
    fun `should set progress bar loading visibility value to gone`(){
        ProgressBarHandler.loading.observeForever(progressBarObserver)

        ProgressBarHandler.hideProgressBar()

        assertNotNull(ProgressBarHandler.loading.value)
        assertNotEquals(ProgressBarHandler.loading.value, View.VISIBLE)

        verify { progressBarObserver.onChanged(View.GONE) }
    }

}