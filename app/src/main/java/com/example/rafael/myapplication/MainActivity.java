package com.example.rafael.myapplication;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private RecyclerView recyclerView;
    private LineAdapter mAdapter;
    private List<Contact> listContact = new ArrayList<>();
    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv);

        mAdapter = new LineAdapter(listContact);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        populateList();

    }

    public void populateList (){


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);

        }else {

          //  Cursor phones = getContentResolver().query(uri,
         //           null, null,
         //           null, ContactsContract.Contacts.SORT_KEY_PRIMARY + " ASC");

         //   int indexName = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
          //  int indexNumber = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);


            ContentResolver cr = getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                    null, null, ContactsContract.Contacts.SORT_KEY_PRIMARY + " ASC");

            if (cur.getCount() > 0) {

                while (cur.moveToNext()) {
                    String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    //Log.i("Names", name);
                    contact = new Contact(name);
                    listContact.add(contact);

                    if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0)
                    {
                        // Query phone here. Covered next
                        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id,null, null);
                        while (phones.moveToNext()) {
                            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                          //  Log.i("Number", phoneNumber);
                            contact.setListPhone(phoneNumber);
                        }
                        phones.close();
                    }

                }
            }

            mAdapter.notifyDataSetChanged();
        }
    }
    
}
