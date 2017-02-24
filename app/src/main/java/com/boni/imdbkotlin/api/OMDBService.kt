package com.boni.imdbkotlin.api

import com.boni.imdbkotlin.models.Movie
import com.boni.imdbkotlin.models.Search
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

class OMDBService {

    fun getOMDBService(): OMDBAPI {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit: Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(OMDBContants.baseUrl)
                .client(client)
                .build()

        return retrofit.create(OMDBAPI::class.java)
    }
}

interface OMDBAPI {
    @GET("/")
    fun getMovieByTitle(@Query("t") title: String, @Query("api_key") api_key: String): Call<Movie>

    @GET("/")
    fun getMoviesByWord(@Query("s") word: String, @Query("api_key") api_key: String): Call<Search>

}