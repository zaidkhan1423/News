package com.example.news.modal

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)