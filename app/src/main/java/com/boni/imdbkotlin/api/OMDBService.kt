package com.boni.imdbkotlin.api

import com.boni.imdbkotlin.models.Movie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class OMDBService {
    companion object {
        val baseUrl: String = "http://www.omdbapi.com"
        val apikey: String = "get_your_key_@_omdbapi.com"
    }

    fun getOMDBService(): OMDBAPI {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit: Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(client)
                .build()

        return retrofit.create(OMDBAPI::class.java)
    }
}

interface OMDBAPI {
    @GET("/")
    fun getMoviesByTitle(@Query("t") Title: String, @Query("api_key") api_key: String): Call<List<Movie>>
}