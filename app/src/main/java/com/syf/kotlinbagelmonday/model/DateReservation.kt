package com.syf.kotlinbagelmonday.model

/**
 * Created by 212464350 on 3/8/17.
 */

class DateReservation(var date: PlaceholderDate, var userDetails:User){

    constructor():this(PlaceholderDate(), User("","",""))

    override fun toString(): String {
        return "DateReservation(date=$date, userDetails=$userDetails)"
    }

}
