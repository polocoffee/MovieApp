package com.example.movieapp.data.remote

import com.example.movieapp.data.model.Shows
import com.example.movieapp.util.Constants.Companion.SHOWS
import retrofit2.Response
import retrofit2.http.GET

interface ShowsApi {

    @GET(SHOWS)
    suspend fun getShows(): Response<Shows>
}