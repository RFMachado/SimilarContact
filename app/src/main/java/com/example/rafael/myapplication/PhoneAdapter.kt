package com.example.rafael.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rafael on 24/12/2017.
 */

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolder> {

    List<String> listPhone;

    public PhoneAdapter(List<String> phone){
        this.listPhone = phone;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.line_adapter, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String phone = listPhone.get(position);
        final Context context = holder.itemView.getContext();

        holder.name.setText(phone);
    }

    @Override
    public int getItemCount() {
        return listPhone.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name =  itemView.findViewById(R.id.name);
        }
    }
}
