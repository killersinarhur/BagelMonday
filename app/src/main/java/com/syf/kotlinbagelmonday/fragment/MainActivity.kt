package com.syf.kotlinbagelmonday.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.syf.kotlinbagelmonday.BaseApplication
import com.syf.kotlinbagelmonday.R
import com.syf.kotlinbagelmonday.adapter.AvailableDateAdapter
import com.syf.kotlinbagelmonday.model.PlaceholderDate

import kotlin.collections.ArrayList

class MainActivity : Fragment() {
    internal var dates: java.util.ArrayList<PlaceholderDate>? = null

    companion object {

        fun newInstance(): com.syf.kotlinbagelmonday.fragment.MainActivity {
            val fragment = com.syf.kotlinbagelmonday.fragment.MainActivity()
            return fragment

        }
    }

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)


        dates= com.syf.kotlinbagelmonday.BaseApplication.Companion.dates


    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater!!.inflate(R.layout.activity_main, container, false)
        val recyclerView = rootView.findViewById(R.id.rv_datelist) as RecyclerView
        val adapter = AvailableDateAdapter(dates as ArrayList<PlaceholderDate>, rootView.context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(rootView.context) as android.support.v7.widget.RecyclerView.LayoutManager?

        return rootView
    }

    override fun onResume() {
        super.onResume()

    }







}
