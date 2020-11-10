package com.marvel.feature_common.base

import java.lang.Exception

/**
 * Base wrapper class for results obtained from asynchronous requests
 */
sealed class Result<out T> {

    data class Loading<T>(val data: T? = null) : Result<T>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

}

val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data

