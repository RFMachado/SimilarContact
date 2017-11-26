package com.example.rafael.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailContact extends AppCompatActivity {

    Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);

        TextView textView1 = findViewById(R.id.number_one);
        TextView textView2 = findViewById(R.id.number_two);
        TextView textView3 = findViewById(R.id.number_three);

        contact = (Contact) getIntent().getSerializableExtra("contact");

        if (contact.phone.size() == 1)
            textView1.setText(contact.phone.get(0));
         else if (contact.phone.size() == 2) {
            textView1.setText(contact.phone.get(0));
            textView2.setText(contact.phone.get(1));
        }
         else if (contact.phone.size() == 3) {
            textView1.setText(contact.phone.get(0));
            textView2.setText(contact.phone.get(1));
            textView3.setText(contact.phone.get(2));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
