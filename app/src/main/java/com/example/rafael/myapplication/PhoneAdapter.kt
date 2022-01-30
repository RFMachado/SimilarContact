package com.example.rafael.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rafael.myapplication.databinding.LineAdapterBinding

/**
 * Created by Rafael on 24/12/2017.
 */
class PhoneAdapter(var listPhone: List<String>) : RecyclerView.Adapter<PhoneAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemBinding: LineAdapterBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(phoneName: String) = with(itemBinding) {
            name.text = phoneName
        }
    }

    override fun getItemCount() = listPhone.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(listPhone[position])
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LineAdapterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemView)
    }
}