package com.syf.kotlinbagelmonday.model

import android.os.Parcelable
import java.io.Serializable

/**
 * Created by 212464350 on 3/8/17.
 */

class DateReservation(var date: PlaceholderDate, var userDetails:User): Serializable{

    constructor():this(PlaceholderDate(), User("","",""))

    override fun toString(): String {
        return "DateReservation(date=$date, userDetails=$userDetails)"
    }

}
