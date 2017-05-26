package com.syf.kotlinbagelmonday.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.FirebaseDatabase
import com.syf.kotlinbagelmonday.BaseApplication
import com.syf.kotlinbagelmonday.R
import com.syf.kotlinbagelmonday.adapter.ReservedByMeAdapter
import com.syf.kotlinbagelmonday.model.DateReservation

class ReservedByMe : Fragment() {
    internal var dates: ArrayList<DateReservation>? = null
    var adapter:ReservedByMeAdapter?= null
    var mAdapter:FirebaseRec<Chat, ChatHolder>? = null

    companion object{
        fun newInstance(): Fragment {
            var fragment= ReservedByMe()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mRef = FirebaseDatabase.getInstance().getReference()
        val mResRef = mRef.child("reservation")
    }

    override fun onStart() {
        super.onStart()

    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dates= BaseApplication.userDates
        val rootView= inflater!!.inflate(R.layout.activity_reserved_date,container,false)
        val recyclerView = rootView.findViewById(R.id.rv_reservedlist) as RecyclerView
        adapter = ReservedByMeAdapter(dates as ArrayList<DateReservation>, rootView.context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(rootView.context) as RecyclerView.LayoutManager?
        return rootView
    }

    override fun onResume() {
        super.onResume()

    }
}
