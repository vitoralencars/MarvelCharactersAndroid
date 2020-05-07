package com.vitor.myapplication.presentation.fragment

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun hideKeyboard(){
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
    }

    fun showKeyboard(){
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(requireActivity().currentFocus, 0)
    }

    fun handleBackPressed(callback: OnBackPressedCallback) =
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

}