package com.example.movieapp.repo

import com.example.movieapp.data.remote.ShowsApi
import javax.inject.Inject

class ShowsRepository @Inject constructor(private val showsApi: ShowsApi) {

    suspend fun getShows() = showsApi.getShows()
}