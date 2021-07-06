  package com.example.myapplication_1

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.myapplication_1.databinding.ActivityMainBinding
import java.lang.Exception
import java.lang.reflect.Constructor

  class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var cursor: Cursor? = requireActivity().contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        activity?.startManagingCursor(cursor)

        var data = ArrayList<Person>()
        var name = ArrayList<String>()
        var number = ArrayList<String>()
        try {
            cursor!!.moveToFirst()
            do {
                name.add(cursor?.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)))
                number.add(cursor?.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)))
            } while (cursor.moveToNext())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        for(i in name.indices){
            val person = Person(name[i], number[i])
            data.add(person)
        }

        val listview = view?.findViewById(R.id.listView) as ListView
        listview.adapter = ContactAdapter(context as Activity, data)


        //검색
        val searchView = view?.findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                var tmp = ArrayList<Person>()
                for(i in data.indices){
                    if(data[i].toString().contains("${query}")==true)
                        tmp.add(data[i])
                }
                listview.adapter = ContactAdapter(context as Activity, tmp)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                var tmp = ArrayList<Person>()
                for(i in data.indices){
                    if(data[i].toString().contains("${newText}")==true)
                        tmp.add(data[i])
                }
                listview.adapter = ContactAdapter(context as Activity, tmp)
                return true
            }
        })


        //누르면 다이얼에
        listview.setOnItemClickListener { parent, view, position, id ->
            var intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${number[position]}")
            if(intent.resolveActivity(requireActivity().packageManager) != null){
                startActivity(intent)
            }
        }

        //길게 누르면
        listview.setOnItemLongClickListener { parent, view, position, id ->
            var intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:${number[position]}")
            if(intent.resolveActivity(requireActivity().packageManager) != null){
                startActivity(intent)
            }
            true
        }

    }
}