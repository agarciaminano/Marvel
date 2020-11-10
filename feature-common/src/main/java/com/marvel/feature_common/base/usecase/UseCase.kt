package com.marvel.feature_common.base.usecase

import com.marvel.feature_common.base.Result

abstract class UseCase<in P, R> {

    suspend operator fun invoke(parameters: P): Result<R> = execute(parameters)

    abstract suspend fun execute(parameters: P): Result<R>
}