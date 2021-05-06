package com.example.hiltexample.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hiltexample.R
import com.example.hiltexample.databinding.FragmentCarsListBinding
import com.example.hiltexample.other.Status
import com.example.hiltexample.ui.adapters.CarAdapter
import com.example.hiltexample.ui.viewmodels.CarsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarsListFragment : Fragment(R.layout.fragment_cars_list) {

    private lateinit var binding: FragmentCarsListBinding
    private lateinit var adapter: CarAdapter
    private lateinit var fContext: Context
    private val carsViewModel: CarsViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.fContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCarsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CarAdapter()

        with(binding) {
            rvCars.layoutManager = LinearLayoutManager(fContext)
            rvCars.adapter = adapter

            carsViewModel.getCars()
            carsViewModel.res.observe(viewLifecycleOwner, Observer { resource ->
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