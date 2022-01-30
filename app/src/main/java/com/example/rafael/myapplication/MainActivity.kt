package com.example.rafael.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rafael.myapplication.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var mAdapter: ContactAdapter? = null
    private val listContact: MutableList<Contact> = ArrayList()
    lateinit var contact: Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAdapter = ContactAdapter(listContact) {
            val intent = DetailContact.launchIntent(this).apply {
                intent.putExtra("contact", contact)
            }

            startActivity(intent)
        }

        bindViews()
        populateList()
    }

    private fun bindViews() = with(binding) {
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
        recyclerView.adapter = mAdapter
    }

    private fun populateList() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                PERMISSIONS_REQUEST_READ_CONTACTS
            )
        } else {
            val cr = contentResolver
            val cur = cr.query(
                ContactsContract.Contacts.CONTENT_URI, null,
                null, null, ContactsContract.Contacts.SORT_KEY_PRIMARY + " ASC"
            )
            if (cur!!.count > 0) {
                while (cur.moveToNext()) {
                    val id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID))
                    val name =
                        cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    //Log.i("Names", name);
                    contact = Contact(name)
                    listContact.add(contact)
                    if (cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                            .toInt() > 0
                    ) {
                        // Query phone here. Covered next
                        val phones = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                            null,
                            null
                        )
                        while (phones!!.moveToNext()) {
                            val phoneNumber =
                                phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            //  Log.i("Number", phoneNumber);
                            contact.setListPhone(phoneNumber)
                        }
                        phones.close()
                    }
                }
            }
            mAdapter?.notifyDataSetChanged()
        }
    }

    companion object {
        private const val PERMISSIONS_REQUEST_READ_CONTACTS = 100
    }
}