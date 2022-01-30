package com.example.rafael.myapplication

import java.io.Serializable
import java.util.ArrayList

/**
 * Created by Rafael on 24/11/2017.
 */
class Contact(
    var name: String,
    var listPhones: MutableList<String> = ArrayList()
) : Serializable {
    fun setListPhone(phone: String) {
        this.listPhones.add(phone)
    }
}