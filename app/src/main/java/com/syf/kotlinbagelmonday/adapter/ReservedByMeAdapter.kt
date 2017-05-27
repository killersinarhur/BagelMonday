package com.syf.kotlinbagelmonday.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.syf.kotlinbagelmonday.MakeReservation
import com.syf.kotlinbagelmonday.ViewReservation
import com.syf.kotlinbagelmonday.fragment.ReservedByMe
import com.syf.kotlinbagelmonday.model.DateReservation

/**
 * Created by 212464350 on 5/9/17.
 */
class ReservedByMeAdapter  (val availableDates: ArrayList<DateReservation>, context: android.content.Context) : RecyclerView.Adapter<ReservedByMeAdapter.ViewHolder>() {
    var context: android.content.Context
        internal set

    init {
        this.context = context
    }

    override fun onBindViewHolder(holder: com.syf.kotlinbagelmonday.adapter.ReservedByMeAdapter.ViewHolder, position: Int) {
        val reservation= availableDates[position]

        holder.dates.text=reservation.date.toString()
        holder.name.text= reservation.userDetails.toGetNameString()

    }

    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int): com.syf.kotlinbagelmonday.adapter.ReservedByMeAdapter.ViewHolder {
        val context = parent.context
        val inflater = android.view.LayoutInflater.from(context)

        val availbleView = inflater.inflate(com.syf.kotlinbagelmonday.R.layout.reserveddate_adapter, parent, false)
        val viewHolder = com.syf.kotlinbagelmonday.adapter.ReservedByMeAdapter.ViewHolder(availbleView)
        return viewHolder

    }

    override fun getItemCount(): Int {
        return availableDates.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal var dates: android.widget.TextView
        internal var name: android.widget.TextView

        init {
            itemView.setOnClickListener(this)

            dates = itemView.findViewById(com.syf.kotlinbagelmonday.R.id.date_reserved) as android.widget.TextView
            name= itemView.findViewById(com.syf.kotlinbagelmonday.R.id.name_of_person) as android.widget.TextView

        }
        override fun onClick(v: View?) {
            val intent = Intent(v?.context, ViewReservation::class.java)
            intent.putExtra("position",adapterPosition)
            v?.context!!.startActivity(intent)

        }
    }

}