package com.syf.kotlinbagelmonday.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.syf.kotlinbagelmonday.MakeReservation
import com.syf.kotlinbagelmonday.R
import com.syf.kotlinbagelmonday.model.PlaceholderDate
import kotlin.collections.ArrayList

/**
 * Created by 212464350 on 3/10/17.
 */

class AvailableDateAdapter(val availableDates: ArrayList<PlaceholderDate>, context: Context) : RecyclerView.Adapter<AvailableDateAdapter.ViewHolder>() {

    var context: Context
        internal set

    init {
        this.context = context
    }




    companion object {
        val sucessCode: Int = 2

    }


    class ViewHolder(itemView: android.view.View) :
            android.support.v7.widget.RecyclerView.ViewHolder(itemView),
            android.view.View.OnClickListener {

        internal var dates: TextView

        init {
            itemView.setOnClickListener(this)

            dates = itemView.
                    findViewById(com.syf.kotlinbagelmonday.R.id.datetext1)
                    as TextView

        }

        override fun onClick(v: View) {
//            Toast.makeText(v.context, adapterPosition.toString(), Toast.LENGTH_LONG).show()
            val intent = Intent(v.context, MakeReservation::class.java)
            intent.putExtra("position", adapterPosition)
            v.context.startActivity(intent)



        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvailableDateAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val availbleView = inflater.inflate(R.layout.rv_adapter, parent, false)
        val viewHolder = AvailableDateAdapter.ViewHolder(availbleView)
        return viewHolder

    }

    override fun onBindViewHolder(holder: AvailableDateAdapter.ViewHolder, position: Int) {
        val date = availableDates[position]

        holder.dates.text = date.toString()


    }

    override fun getItemCount(): Int {
        return availableDates.size
    }


}
