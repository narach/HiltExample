package com.example.hiltexample.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.hiltexample.R
import com.example.hiltexample.databinding.ActivityMainBinding
import com.example.hiltexample.ui.viewmodels.CarsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Enable Dagger Hilt to inject objects here
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val carsViewModel: CarsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment ?: return
        val navController = host.navController
        setupBottomNavMenu(navController)

        with(binding) {
        }
    }

    private fun setupBottomNavMenu(navController: NavController) {
        with(binding) {
            bottomNavView.setupWithNavController(navController)
        }
    }
}