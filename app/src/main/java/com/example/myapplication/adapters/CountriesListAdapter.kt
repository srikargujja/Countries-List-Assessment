package com.example.myapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.CountryItemBinding
import com.example.myapplication.models.CountriesListModelItem


class CountriesListAdapter(private val context : Context) : RecyclerView.Adapter<CountriesVH>() {
    var countriesList = emptyList<CountriesListModelItem>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesVH {
        val binding = CountryItemBinding.inflate(LayoutInflater.from(parent.context))
        return CountriesVH(binding)
    }
    override fun getItemCount(): Int {
        return countriesList.size
    }
    override fun onBindViewHolder(holder: CountriesVH, position: Int) {
        val country = countriesList[position]
        holder.bind(country , context)
    }
}
class CountriesVH(private val binding: CountryItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(country: CountriesListModelItem, context: Context) {
        binding.cityName.text = "Name : ${country.name}"
        binding.cityCapital.text = "Capital : ${country.capital}"
        binding.cityCode.text = "Code : ${country.code}"
  /*      Glide.with(context)
            .load(country.flag)
            .error(R.drawable.ic_launcher_background)
            .into(binding.cityImage)*/

    }
}