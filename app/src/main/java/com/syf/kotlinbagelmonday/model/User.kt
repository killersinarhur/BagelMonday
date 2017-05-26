package com.syf.kotlinbagelmonday.model

/**
 * Created by 212464350 on 5/9/17.
 */
class User(var firstName: String, var lastName:String, var phoneNumber:String) {

    constructor():this("","","")

    fun toGetNameString(): String {
        return firstName + " "+lastName
    }

    override fun toString(): String {
        return "User(firstName='$firstName', lastName='$lastName', phoneNumber='$phoneNumber')"
    }

}