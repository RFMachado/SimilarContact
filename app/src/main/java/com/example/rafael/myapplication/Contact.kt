package com.example.rafael.myapplication;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 24/11/2017.
 */

public class Contact implements Serializable {

    String name;
    List<String> phone = new ArrayList<>();

    public  Contact (String name){
        this.name = name;
    }

    public void setListPhone(String phone){
        this.phone.add(phone);
    }


    public List<String> getListPhone(){
        return this.phone;
    }
}
