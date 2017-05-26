package com.syf.kotlinbagelmonday.model

import kotlin.collections.ArrayList

/**
 * Created by 212464350 on 5/21/17.
 */

class DateReservations(var dateReservations: ArrayList<DateReservation>){
    constructor():this(ArrayList())

    override fun toString(): String {
        return "DateReservations(dateReservations=$dateReservations)"
    }
}

