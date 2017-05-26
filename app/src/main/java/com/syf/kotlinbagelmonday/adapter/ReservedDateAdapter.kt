package com.syf.kotlinbagelmonday.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.syf.kotlinbagelmonday.model.DateReservation
import com.syf.kotlinbagelmonday.R

/**
 * Created by 212464350 on 5/8/17.
 */

class ReservedDateAdapter (val availableDates: ArrayList<DateReservation>, context: Context) : RecyclerView.Adapter<ReservedDateAdapter.ViewHolder>() {
    var context: Context
        internal set

    init {
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reservation= availableDates[position]

        holder.dates.text=reservation.date.toString()
        holder.name.text= reservation.userDetails.toGetNameString()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val availbleView = inflater.inflate(R.layout.reserveddate_adapter, parent, false)
        val viewHolder = ViewHolder(availbleView)
        return viewHolder

    }

    override fun getItemCount(): Int {
       return availableDates.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal var dates: TextView
        internal var name: TextView

        init {
            itemView.setOnClickListener(this)

            dates = itemView.findViewById(R.id.date_reserved) as TextView
            name= itemView.findViewById(R.id.name_of_person) as TextView

        }
        override fun onClick(v: View?) {
            Toast.makeText(itemView.context,"test", Toast.LENGTH_LONG).show()
        }

    }

}
