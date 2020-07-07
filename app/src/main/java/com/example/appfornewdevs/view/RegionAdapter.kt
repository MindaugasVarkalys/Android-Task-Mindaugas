package com.example.appfornewdevs.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.appfornewdevs.BR
import com.example.appfornewdevs.R
import com.example.appfornewdevs.databinding.ItemRegionBinding
import com.example.appfornewdevs.models.RegionModel

class RegionAdapter (private var items: List<RegionModel>) :
    RecyclerView.Adapter<RegionAdapter.BindingHolder>() {

    private val layoutId = R.layout.item_region
    private lateinit var binding: ItemRegionBinding

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
        return BindingHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class BindingHolder(private val binding: ItemRegionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: RegionModel)  {
            binding.setVariable(BR.model, model)
            binding.executePendingBindings()
        }
    }

}