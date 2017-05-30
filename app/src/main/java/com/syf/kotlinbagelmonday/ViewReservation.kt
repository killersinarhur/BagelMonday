package com.syf.kotlinbagelmonday

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.syf.kotlinbagelmonday.fragment.ReservedByMe
import com.syf.kotlinbagelmonday.model.DateReservation
import java.util.*


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


        val addToCalendar= findViewById(R.id.add_to_calendar) as Button
        addToCalendar.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                 addDateToCalendar(itemSelected)
            }
        })
        val deleteReservation=findViewById(R.id.cancel_reservation) as Button
        deleteReservation.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                Toast.makeText(this@ViewReservation,
                        "Implement Swap Process",
                        Toast.LENGTH_LONG)
                        .show()
            }
        })

    }

    private fun addDateToCalendar(itemSelected: DateReservation) {
        val calendarIntent= Intent(Intent.ACTION_INSERT)
        calendarIntent.setData(CalendarContract.Events.CONTENT_URI)
        calendarIntent.putExtra(CalendarContract.Events.TITLE,"Bagel Monday")
        calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION,"Innovation Station")
        calendarIntent.putExtra(CalendarContract.Events.ALL_DAY,true)
        val calendar: GregorianCalendar= GregorianCalendar(itemSelected.date.year,
                                                                    itemSelected.date.month-1,
                                                                    itemSelected.date.day)
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,calendar.timeInMillis)
        calendarIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,calendar.timeInMillis)
        startActivity(calendarIntent)
   }
}
