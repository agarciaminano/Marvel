package com.marvel.feature_common.base.usecase

abstract class SingleUseCase<P, R> {

    suspend operator fun invoke(params: P) : R = execute(params)
    abstract suspend fun execute(params: P) : R
}