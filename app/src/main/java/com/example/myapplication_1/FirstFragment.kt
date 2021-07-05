  package com.example.myapplication_1

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.fragment.app.Fragment
import com.example.myapplication_1.databinding.ActivityMainBinding

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

        var from = arrayOf(
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone._ID
        )
        print(from)
        var to = intArrayOf(android.R.id.text1, android.R.id.text2)

        var simple: SimpleCursorAdapter =
            SimpleCursorAdapter(activity, android.R.layout.simple_list_item_2, cursor, from, to)
        val listview = view?.findViewById(R.id.listView) as ListView
        listview.setAdapter(simple)

        //누르면 다이얼에
        listview.setOnItemClickListener { parent, view, position, id ->
            var intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${cursor?.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))}")
            if(intent.resolveActivity(requireActivity().packageManager) != null){
                startActivity(intent)
            }
        }

//        //누르면 전화걸기
//        listview.setOnItemClickListener { parent, view, position, id ->
//            var intent = Intent(Intent.ACTION_CALL)
//            intent.data = Uri.parse("tel:${cursor?.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))}")
//            if(intent.resolveActivity(requireActivity().packageManager) != null){
//                startActivity(intent)
//            }
//        }
    }
}