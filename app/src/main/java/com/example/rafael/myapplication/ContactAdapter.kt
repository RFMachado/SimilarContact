package com.example.rafael.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rafael.myapplication.databinding.LineAdapterBinding

/**
 * Created by Rafael on 24/11/2017.
 */
class ContactAdapter(
    private var listContact: List<Contact>,
    private val listener: (Contact) -> Unit
) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemBinding: LineAdapterBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(contact: Contact) = with(itemBinding) {
            name.text = contact.name
            root.setOnClickListener {
                listener.invoke(contact)
            }
        }
    }

    override fun getItemCount() = listContact.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(listContact[position])
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LineAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemView)
    }
}