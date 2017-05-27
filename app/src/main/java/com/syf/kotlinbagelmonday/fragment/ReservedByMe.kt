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
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.syf.kotlinbagelmonday.BaseApplication
import com.syf.kotlinbagelmonday.R
import com.syf.kotlinbagelmonday.ViewReservation
import com.syf.kotlinbagelmonday.adapter.ReservedByMeAdapter
import com.syf.kotlinbagelmonday.model.DateReservation

class ReservedByMe : Fragment() {
    internal var dates: ArrayList<DateReservation>? = null

    val mref=FirebaseDatabase.getInstance().reference
    val mDRRef= mref.child("reservation")
    val auth=FirebaseAuth.getInstance()

    companion object{
        var mAdapter:FirebaseRecyclerAdapter<DateReservation,ReservedByMeAdapter.ViewHolder >? = null
        fun newInstance(): Fragment {
            var fragment= ReservedByMe()
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val query:Query= mDRRef.child(auth.currentUser!!.uid).limitToFirst(50)
        mAdapter= object:FirebaseRecyclerAdapter<DateReservation, ReservedByMeAdapter.ViewHolder>(DateReservation::class.java, R.layout.reserveddate_adapter, ReservedByMeAdapter.ViewHolder::class.java, query) {

            override fun populateViewHolder(viewHolder: ReservedByMeAdapter.ViewHolder, model: DateReservation, position: Int) {

                viewHolder.dates.text=model.date.toString()
                viewHolder.name.text= model.userDetails.toGetNameString()


            }
        }
    }


    private fun attachRV() {
        val query:Query= mDRRef.limitToLast(10)
        mAdapter= object:FirebaseRecyclerAdapter<DateReservation, ReservedByMeAdapter.ViewHolder>(DateReservation::class.java, R.layout.reserveddate_adapter, ReservedByMeAdapter.ViewHolder::class.java, query) {
            override fun populateViewHolder(viewHolder: ReservedByMeAdapter.ViewHolder, model: DateReservation, position: Int) {

                viewHolder.dates.text=model.date.toString()
                viewHolder.name.text= model.userDetails.toGetNameString()


            }
        };


    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView= inflater!!.inflate(R.layout.activity_reserved_date,container,false)
        val recyclerView = rootView.findViewById(R.id.rv_reservedlist) as RecyclerView
        recyclerView.adapter=mAdapter
        recyclerView.layoutManager = LinearLayoutManager(rootView.context) as RecyclerView.LayoutManager?
        return rootView
    }

    override fun onResume() {
        super.onResume()

    }
}
