package com.example.rafael.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rafael.myapplication.databinding.ActivityDetailContactBinding

class DetailContact : AppCompatActivity() {
    lateinit var contact: Contact
    private var mAdapter: PhoneAdapter? = null
    lateinit var binding: ActivityDetailContactBinding

    companion object {
        fun launchIntent(context: Context) = Intent(context, DetailContact::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contact = intent.getSerializableExtra("contact") as Contact
        bindViews()
    }

    private fun bindViews() = with(binding) {
        mAdapter = PhoneAdapter(contact.listPhones)

        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(this@DetailContact, LinearLayoutManager.VERTICAL))
        recyclerView.adapter = mAdapter
        mAdapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}