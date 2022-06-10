package com.music.common.impl

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.InvocationTargetException

class DefaultViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    companion object {
        private val defalutViewModel by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            DefaultViewModelFactory()
        }

        fun getInstance() = defalutViewModel
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (AndroidViewModel::class.java.isAssignableFrom(modelClass)) {
            try {
                modelClass.getConstructor().newInstance()
            } catch (e: NoSuchMethodException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InstantiationException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            } catch (e: InvocationTargetException) {
                throw RuntimeException("Cannot create an instance of $modelClass", e)
            }
        } else super.create(modelClass)
    }

}