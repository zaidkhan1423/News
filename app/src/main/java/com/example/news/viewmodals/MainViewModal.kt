package com.example.news.viewmodals

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.modal.Article
import com.example.news.modal.NewsResponse
import com.example.news.repository.NewsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModal(private val repository: NewsRepository): ViewModel() {

    val newsList: LiveData<NewsResponse> = repository.news

    fun getNews(page: Int,category: String){
        viewModelScope.launch {
            repository.getHeadlines(page,category)
        }
    }

}

