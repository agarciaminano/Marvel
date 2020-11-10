package com.marvel.feature_common.base.ui

import androidx.lifecycle.ViewModel
import com.marvel.feature_common.base.Result

abstract class BaseViewModel : ViewModel() {



    @Suppress("uncheckedCast")
    fun <T, R> handleResult(result: Result<T>, onSuccess: (T) -> R): Result<R> {
        return when (result) {
            is Result.Success -> Result.Success(onSuccess(result.data))
            is Result.Loading -> if (result.data != null) {
                Result.Loading(onSuccess(result.data!!))
            } else {
                result
            }
            is Result.Error -> result
        } as Result<R>
    }
}