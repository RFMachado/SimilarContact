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
    var contact: Contact? = null
    private var mAdapter: PhoneAdapter? = null
    lateinit var binding: ActivityDetailContactBinding

    companion object {
        const val EXTRA_CONTACT = "contact"

        fun launchIntent(context: Context, contact: Contact): Intent {
            val intent = Intent(context, DetailContact::class.java)
            intent.putExtra(EXTRA_CONTACT, contact)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contact = intent.getSerializableExtra(EXTRA_CONTACT) as? Contact
        bindViews()
    }

    private fun bindViews() = with(binding) {
        contact?.listPhones?.let {
            mAdapter = PhoneAdapter(it)

            recyclerView.apply {
                itemAnimator = DefaultItemAnimator()
                addItemDecoration(DividerItemDecoration(this@DetailContact, LinearLayoutManager.VERTICAL))
                adapter = mAdapter
            }
        }

        mAdapter?.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}