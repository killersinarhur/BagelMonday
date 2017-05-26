package com.syf.kotlinbagelmonday.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.syf.kotlinbagelmonday.BaseApplication
import com.syf.kotlinbagelmonday.R
import com.syf.kotlinbagelmonday.adapter.ReservedByMeAdapter
import com.syf.kotlinbagelmonday.adapter.ReservedDateAdapter
import com.syf.kotlinbagelmonday.model.DateReservation

class ReservedDate : Fragment() {
    internal var dates: ArrayList<DateReservation>? = null
    var mAdapter: FirebaseRecyclerAdapter<DateReservation, ReservedDateAdapter.ViewHolder>? = null
    val mref= FirebaseDatabase.getInstance().reference
    val mDRRef= mref.child("reservation")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val query: Query = mDRRef.limitToFirst(50)
        mAdapter= object:FirebaseRecyclerAdapter<DateReservation, ReservedDateAdapter.ViewHolder>(DateReservation::class.java, R.layout.reserveddate_adapter, ReservedDateAdapter.ViewHolder::class.java, query) {
            override fun populateViewHolder(viewHolder: ReservedDateAdapter.ViewHolder, model: DateReservation, position: Int) {
                viewHolder.dates.text=model.date.toString()
                viewHolder.name.text= model.userDetails.toGetNameString()

            }

        };

    }

    companion object{

        fun newInstance(): ReservedDate {
            val fragment = ReservedDate()
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        dates= BaseApplication.reservedDate
        val rootView= inflater!!.inflate(R.layout.activity_reserved_date,container,false)
        val recyclerView = rootView.findViewById(R.id.rv_reservedlist) as RecyclerView
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(rootView.context) as RecyclerView.LayoutManager?
        return rootView
    }

    override fun onResume() {
        super.onResume()

    }
}
