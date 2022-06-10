package com.music.libase.provider

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


inline fun <reified T : ViewModel> Application.createViewModel(): T {
    return ViewModelProvider.AndroidViewModelFactory(this).create(T::class.java)
}

inline fun <reified T : ViewModel> AppCompatActivity.createViewModel(): T {
    return ViewModelProvider(
        viewModelStore,
        ViewModelProvider.AndroidViewModelFactory(application)
    ).get(T::class.java)
}

inline fun <reified T : ViewModel> Fragment.createViewModel(): T {
    return ViewModelProvider(
        viewModelStore,
        ViewModelProvider.AndroidViewModelFactory(requireContext().applicationContext as Application)
    ).get(T::class.java)
}

fun <T : ViewModel> Application.createViewModel(clz: Class<T>): T {
    return ViewModelProvider.AndroidViewModelFactory(this).create(clz)
}

fun <T : ViewModel> AppCompatActivity.createViewModel(clz: Class<T>): T {
    return ViewModelProvider(
        viewModelStore,
        ViewModelProvider.AndroidViewModelFactory(application)
    ).get(clz)
}

fun <T : ViewModel> Fragment.createViewModel(clz: Class<T>): T {
    return ViewModelProvider(
        viewModelStore,
        ViewModelProvider.AndroidViewModelFactory(requireContext().applicationContext as Application)
    ).get(clz)
}

