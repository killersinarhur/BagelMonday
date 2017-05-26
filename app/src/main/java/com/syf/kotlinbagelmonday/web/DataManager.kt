package com.syf.kotlinbagelmonday.web

import android.util.Log

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.syf.kotlinbagelmonday.BaseApplication
import com.syf.kotlinbagelmonday.model.DateReservation
import com.syf.kotlinbagelmonday.model.DateReservations
import com.syf.kotlinbagelmonday.model.User
import kotlin.collections.ArrayList


/**
 * Created by 212464350 on 5/18/17.
 */

class DataManager {

    companion object {

        val instance= DataManager()

        }


    }

