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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        getMoviesByTitle()
    }

    fun getMoviesByTitle() {

        val omdbService = OMDBService().getOMDBService()
        omdbService.getMoviesByTitle(Title = "Matrix", api_key = OMDBContants.apikey).enqueue(object : Callback<List<Movie>> {
            override fun onResponse(p0: Call<List<Movie>>?, response: Response<List<Movie>>?) {
                val response = response?.body()
            }

            override fun onFailure(p0: Call<List<Movie>>?, t: Throwable?) {
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