package com.alejandro.marvel.interceptor

import com.marvel.feature_common.extensions.md5
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val publickey: String, private val privateKey: String, private val timeStamp: Long) :
    Interceptor {
    companion object {
        const val API_KEY_PARAM = "apikey"
        const val TS_PARAM = "ts"
        const val HASH_PARAM = "hash"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val httpUrl = request.url().newBuilder()
            .addQueryParameter(API_KEY_PARAM, publickey)
            .addQueryParameter(TS_PARAM, timeStamp.toString())
            .addQueryParameter(HASH_PARAM, "$timeStamp$privateKey$publickey".md5())
            .build()
        val modifiedRequest = request.newBuilder().url(httpUrl).build()
        return chain.proceed(modifiedRequest)
    }
}