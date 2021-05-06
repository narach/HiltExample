package com.example.hiltexample.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltexample.databinding.CarItemBinding
import com.example.hiltexample.models.Content

class CarAdapter: RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    class CarViewHolder(private val carItemBinding: CarItemBinding):
        RecyclerView.ViewHolder(carItemBinding.root) {

        fun bind(carItem: Content) {
            with(carItemBinding) {
                tvBrand.text = carItem.brand.name
                tvModel.text = carItem.model.name
                tvYear.text = carItem.year.toString()
            }
        }

        companion object {
            fun create(parent: ViewGroup) : CarViewHolder {
                val carItemBinding = CarItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return CarViewHolder(carItemBinding)
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(oldItem: Content, newItem: Content): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Content, newItem: Content): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<Content>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val carItem = differ.currentList[position]
        holder.bind(carItem)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}