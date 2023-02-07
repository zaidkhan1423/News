package com.example.news.di

import com.example.news.retrofit.NewsAPI
import com.example.news.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun getNewsAPI(retrofit: Retrofit): NewsAPI{
        return retrofit.create(NewsAPI::class.java)
    }
}