package com.apps.userlistviewnregistration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = mutableListOf<String>("Shivam", "amit", "hello", "world", "team rakesh")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")
        data.add("sfkas")

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Users"

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)

        val listView: ListView = findViewById(R.id.listView)

        listView.adapter = adapter
    }
}