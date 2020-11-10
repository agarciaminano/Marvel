package com.alejandro.marvel.di.modules

import android.content.Context
import com.alejandro.marvel.R
import com.alejandro.marvel.interceptor.AuthInterceptor
import com.marvel.feature_home.data.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideCharacterService(@ApplicationContext context: Context): CharacterService {
        val retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.marvel_api_url))
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient()
                    .newBuilder()
                    .addInterceptor(
                        AuthInterceptor(
                            publickey = context.getString(R.string.marvel_public_api_key),
                            privateKey = context.getString(R.string.marvel_private_api_key),
                            timeStamp = Date().time
                        )
                    )
                    .build()
            )
            .build()
        return retrofit.create(CharacterService::class.java)
    }
}