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
    String phoneNumber, name;
    Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    private List<String> listcompare = new ArrayList<>();
    Contact contact;

    String[] projection = new String[] {
            ContactsContract.Contacts.DISPLAY_NAME,
    };

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

            Cursor phones = getContentResolver().query(uri,
                    projection, null,
                    null, ContactsContract.Contacts.SORT_KEY_PRIMARY + " ASC");

            int indexName = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
           // int indexID = phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID);



            if (phones.moveToFirst()) {

                do {

                    name = phones.getString(indexName);
                    System.out.println("---" + name);

                    contact = new Contact(name);
                    listContact.add(contact);

                } while (phones.moveToNext());

            }

            phones.close();

            mAdapter.notifyDataSetChanged();
        }
    }
    
}
