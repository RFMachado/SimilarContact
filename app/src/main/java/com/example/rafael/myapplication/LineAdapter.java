package com.example.rafael.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 24/11/2017.
 */

public class LineAdapter extends RecyclerView.Adapter<LineAdapter.ViewHolder> {

    List<Contact> listContact ;

    public LineAdapter(List<Contact> listContact){
        this.listContact = listContact;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.line_adapter, parent, false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact contact = listContact.get(position);

        holder.name.setText(contact.name);
    }

    @Override
    public int getItemCount() {
        return listContact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public ViewHolder(View view) {
            super(view);
            name =  view.findViewById(R.id.name);

        }
    }
}
