package com.boni.imdbkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.boni.imdbkotlin.api.OMDBContants
import com.boni.imdbkotlin.api.OMDBService
import com.boni.imdbkotlin.models.Movie
import com.boni.imdbkotlin.models.Search
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        getMoviesByTitle()
    }

    fun getMovieByTitle() {
        val omdbService = OMDBService().getOMDBService()
        omdbService.getMovieByTitle(title = "Star Wars", api_key = OMDBContants.apikey).enqueue(object : Callback<Movie> {
            override fun onResponse(p0: Call<Movie>, response: Response<Movie>) {
                val movie: Movie = response.body()
                println("Test = " + movie.Title)
            }

            override fun onFailure(p0: Call<Movie>?, t: Throwable?) {
                Log.e("APIClient", t?.message)
            }
        })
    }

    fun getMoviesByTitle() {
        val omdbService = OMDBService().getOMDBService()
        omdbService.getMoviesByWord(word = "Star Wars", api_key = OMDBContants.apikey).enqueue(object : Callback<Search> {
            override fun onResponse(p0: Call<Search>, response: Response<Search>) {
                val result: Search = response.body()
                println("Test = " + result.Search.get(0).Title)
            }

            override fun onFailure(p0: Call<Search>?, t: Throwable?) {
                Log.e("APIClient", t?.message)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}