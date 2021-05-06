package com.example.hiltexample.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.hiltexample.R
import com.example.hiltexample.databinding.FragmentAddCarBinding
import com.example.hiltexample.databinding.FragmentCarsListBinding
import com.example.hiltexample.ui.viewmodels.CarsViewModel

class AddCarFragment : Fragment(R.layout.fragment_add_car) {

    private lateinit var binding: FragmentAddCarBinding
    private lateinit var fContext: Context
    private val carsViewModel: CarsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}