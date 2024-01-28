package com.apps.userlistviewnregistration


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.apps.userlistviewnregistration.model.CombinedDataEntity


class CustomAdapter(private val context: Context, private val itemList: List<CombinedDataEntity>) : BaseAdapter() {

    override fun getCount(): Int = itemList.size

    override fun getItem(position: Int): Any = itemList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false)
        }

        val name:TextView = view!!.findViewById(R.id.fname)
        val city: TextView = view.findViewById(R.id.city)



        val currentItem = getItem(position)

        Log.d("current",currentItem.toString())

         name.text = "${itemList[position].UserRegistration.first_name} ${itemList[position].UserRegistration.last_name}"
        city.text = "${itemList[position].addressInfo.city}, ${itemList[position].addressInfo.state}"




        return view
    }
}
