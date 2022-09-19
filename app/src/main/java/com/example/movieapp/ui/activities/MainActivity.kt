package com.example.movieapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.movieapp.data.model.Show
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.ui.detail.DetailActivity
import com.example.movieapp.util.Constants.Companion.SHOW_ID
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val showsViewModel: ShowsViewModel by viewModels()
    private lateinit var showsAdapter: ShowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showsAdapter = ShowAdapter()

        setUpRecyclerView()
        getShows()
        observeShows()
        setOnCardViewClick()
        disableViews()
    }


    private fun getShows() {
        showsViewModel.getShows()
    }

    private fun setUpRecyclerView() {
        binding.mRecyclerView.apply {
            adapter = showsAdapter
        }
    }

    private fun observeShows() {
        showsViewModel.observeShows().observe(this) {
            showsAdapter.setShows(it)
            enableViews()
        }
    }

    private fun setOnCardViewClick() {
        showsAdapter.setOnCardViewClick(object : ShowAdapter.OnCardViewClick {
            override fun onClick(show: Show) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(SHOW_ID, show.id)
                startActivity(intent)
            }

        })
    }

    private fun enableViews() {
        binding.mRecyclerView.visibility = View.VISIBLE
        binding.mProgressBar.visibility = View.GONE
    }

    private fun disableViews(){
        binding.mRecyclerView.visibility = View.GONE
        binding.mProgressBar.visibility = View.VISIBLE
    }


}