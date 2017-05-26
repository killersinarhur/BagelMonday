package com.syf.kotlinbagelmonday.model

import java.io.Serializable

/**
 * Created by 212464350 on 3/10/17.
 */

class PlaceholderDate(var month: Int, var day: Int, var year: Int){

    constructor():this(0,0,0)

    override fun toString(): String {
        return month.toString() + "," + day + "," + year
    }




}
