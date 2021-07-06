package com.example.myapplication_1

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ContactAdapter(private val context: Activity, private val arrayList: ArrayList<Person>): ArrayAdapter<Person>(context,
R.layout.list_item, arrayList) {
    override fun getView(position: Int, converView: View?, parent: ViewGroup): View{
        val inflater:LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.list_item, null)

        val textview1: TextView=view.findViewById(R.id.textView1)
        val textview2: TextView=view.findViewById(R.id.textView2)

        textview1.text = arrayList[position].name
        textview2.text = arrayList[position].number

        return view
    }
}