  package com.example.myapplication_1

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment

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

        var cursor : Cursor? = requireActivity().contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
        activity?.startManagingCursor(cursor)

        var from = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone._ID)
        var to = intArrayOf(android.R.id.text1, android.R.id.text2)

        var simple : SimpleCursorAdapter = SimpleCursorAdapter(activity, android.R.layout.simple_list_item_2, cursor, from, to)
        val listview = view?.findViewById(R.id.listView) as ListView
        listview.setAdapter(simple)
    }


    fun newInstant():FirstFragment{
        val args = Bundle()
        val frag = FirstFragment()
        frag.arguments = args
        return frag
    }
}