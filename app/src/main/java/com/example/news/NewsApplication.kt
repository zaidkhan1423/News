package com.example.news

import android.app.Application
import com.example.news.di.ApplicationComponent
import com.example.news.di.DaggerApplicationComponent

class NewsApplication: Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}