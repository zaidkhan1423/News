package com.example.news.retrofit

import com.example.news.modal.NewsResponse
import com.example.news.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode: String = "in",
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("category") category: String = ""
    ): Response<NewsResponse>

}