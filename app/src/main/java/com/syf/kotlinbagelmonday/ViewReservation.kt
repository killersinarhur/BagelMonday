package com.syf.kotlinbagelmonday

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.syf.kotlinbagelmonday.fragment.ReservedByMe
import com.syf.kotlinbagelmonday.model.DateReservation


class ViewReservation : AppCompatActivity() {

    internal var reservation:Int?=null
    internal var datez:DateReservation?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_reservation)
        reservation= intent.getIntExtra("position",0)
        val itemSelected=ReservedByMe.mAdapter!!.getItem(reservation!!)

        val dateResvered= findViewById(R.id.reserved_date_vr) as TextView
        dateResvered.setText(itemSelected.date.toString())
        val reservedBy= findViewById(R.id.reserved_by_vr) as TextView
        reservedBy.setText(itemSelected.userDetails.toGetNameString())

    }
}
