package com.example.news.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.news.modal.NewsResponse
import com.example.news.retrofit.NewsAPI
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsAPI: NewsAPI) {

    private val _news = MutableLiveData<NewsResponse>()
    val news: LiveData<NewsResponse> = _news

    suspend fun getHeadlines(page: Int,category: String){
        val result = newsAPI.getBreakingNews(pageNumber = page, category = category)
        if (result?.body() != null){
            _news.postValue(result.body())
        }
    }

}

