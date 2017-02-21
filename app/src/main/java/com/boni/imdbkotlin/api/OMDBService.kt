package com.boni.imdbkotlin.api

import android.content.Context
import com.boni.imdbkotlin.R
import com.boni.imdbkotlin.models.Movie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class OMDBService constructor(context: Context) {
    val baseUrl: String = context.getString(R.string.omdbapi_url)
    val apikey: String = context.getString(R.string.omdbapi_key)

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