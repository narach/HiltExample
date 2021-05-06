package com.example.hiltexample.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hiltexample.databinding.ActivityMainBinding
import com.example.hiltexample.other.Status
import com.example.hiltexample.ui.adapters.CarAdapter
import com.example.hiltexample.ui.viewmodels.CarsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Enable Dagger Hilt to inject objects here
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CarAdapter
    private val carsViewModel: CarsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CarAdapter()

        with(binding) {
            rvCars.layoutManager = LinearLayoutManager(this@MainActivity)
            rvCars.adapter = adapter

            btnLoadCars.setOnClickListener {
                carsViewModel.getCars()
                carsViewModel.res.observe(this@MainActivity, Observer { resource ->
                    when(resource.status) {
                        Status.SUCCESS -> {
                            progress.visibility = View.GONE
                            rvCars.visibility = View.VISIBLE
                            resource.data?.let { res ->
                                adapter.submitList(res.content)
                            }
                        }
                        Status.LOADING -> {
                            progress.visibility = View.VISIBLE
                            rvCars.visibility = View.GONE
                        }
                        Status.ERROR -> {
                            progress.visibility = View.GONE
                            rvCars.visibility = View.VISIBLE
                            Snackbar.make(
                                rootView,
                                "Something went wrong",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                    }
                })
            }
        }
    }
}