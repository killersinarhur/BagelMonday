package com.syf.kotlinbagelmonday

import android.app.Application
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.syf.kotlinbagelmonday.model.DateReservation
import com.syf.kotlinbagelmonday.model.PlaceholderDate
import com.syf.kotlinbagelmonday.model.User
import com.syf.kotlinbagelmonday.web.DataManager
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by 212464350 on 3/8/17.
 */

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        dayholder = Calendar.getInstance()
        //intializeUser()
        getFutureDates(8)
       // masterTakenList.addAll(reservedDate as ArrayList<DateReservation>)
//        getAllReservations()
        //organizeList()
    }

    companion object {
        var dates: ArrayList<PlaceholderDate>? = ArrayList<PlaceholderDate>()
        var reservedDate: ArrayList<DateReservation>? = ArrayList<DateReservation>()
        var userDates: ArrayList<DateReservation>? = ArrayList<DateReservation>()
        var masterTakenList:ArrayList<DateReservation>?=ArrayList<DateReservation>()
        var user: User? = null

        fun organizeList() {
            organizeReservedDate()
            organizeUserDates()

        }

        fun getAllReservations() {

            val reservationdb = FirebaseDatabase.getInstance().getReference("reservation")
            val query = reservationdb.orderByValue()
            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError?) {
                    Log.e("DataManager", p0?.code.toString())
                    Log.e("DataManager", p0?.message)
                    Log.e("DataManager", p0?.details)
                }

                override fun onDataChange(p0: DataSnapshot?) {
                    val arrayToSet = ArrayList<DateReservation>()
                    Log.d("GAR-DM-1", p0.toString())
                    Log.d("GAR-DM-2", p0!!.getValue().toString())
                    Log.d("GAR-DM-3", p0!!.childrenCount.toString())
                    val iterator = p0.children.iterator()
                    val rangeEnds = p0.childrenCount - 1
                    for (i in 0..rangeEnds) {
                        val nextValue = iterator.next()
                        if (nextValue.key !="dummy"){
                            Log.d("GAR-DM-4", nextValue.toString())
                            Log.d("GAR-DM-5", nextValue.getValue().toString())
                            Log.d("GAR-DM-6", nextValue.children.iterator()
                                    .next().getValue(DateReservation::class.java).toString())
                            val rezDate = nextValue.children.iterator()
                                    .next().getValue(DateReservation::class.java)
                            arrayToSet.add(rezDate)


                        }


                    }

                    masterTakenList=arrayToSet
                    organizeList()

                }



            })





        }

        fun organizeUserDates() {

            if (reservedDate!!.size>0) {
                val iterator: Iterator<DateReservation>? = reservedDate!!.iterator()

                while (iterator!!.hasNext()) {
                    var checkValue = iterator.next()
                    Log.d("OUD-BA-1",checkValue.userDetails.toGetNameString())
                    Log.d("OUD-BA-2", user!!.toGetNameString())
                    if (checkValue.userDetails.toGetNameString() == user!!.toGetNameString()) {
                        userDates?.add(checkValue)
                    }
                }
            }

            reservedDate = removeDates(userDates, masterTakenList )


        }

        private fun removeDates(list1: ArrayList<DateReservation>?, list2: ArrayList<DateReservation>?): ArrayList<DateReservation> {
            var unionList: ArrayList<DateReservation> = ArrayList<DateReservation>(list1)
            var intersectionList: ArrayList<DateReservation> = ArrayList<DateReservation>(list1)
            unionList.addAll(list2!!)
            intersectionList.retainAll(list2)
            unionList.removeAll(intersectionList)

            return unionList

        }

        private fun organizeReservedDate() {
            var temp: ArrayList<PlaceholderDate>? = ArrayList<PlaceholderDate>()
            val iterator: Iterator<DateReservation>? = reservedDate!!.iterator()

            while (iterator!!.hasNext()) {
                var checkValue = iterator.next()
                for (y in dates!!) {
                    if (checkValue.date.toString() == y.toString()) {
                        temp!!.add(y)
                    }
                }
            }
            dates = removeSameDates(temp, dates)

            getFutureDates(temp!!.size)

        }

        private fun removeSameDates(list3: ArrayList<PlaceholderDate>?, list4: ArrayList<PlaceholderDate>?):
                ArrayList<PlaceholderDate>? {
            var unionList: ArrayList<PlaceholderDate> = ArrayList<PlaceholderDate>(list3)
            var intersectionList: ArrayList<PlaceholderDate> = ArrayList<PlaceholderDate>(list3)
            unionList.addAll(list4!!)
            intersectionList.retainAll(list4)
            unionList.removeAll(intersectionList)

            return unionList

        }


        private fun getFutureDates(loop: Int) {
            for (i in 0..loop) {
                getNextMonday()
                val temp = PlaceholderDate(dayholder!!.get(Calendar.MONTH) + 1,
                        dayholder!!.get(Calendar.DAY_OF_MONTH),
                        dayholder!!.get(Calendar.YEAR))
                dates?.add(temp)
            }
        }

        private fun getNextMonday() {
            while (dayholder?.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                dayholder?.add(Calendar.DATE, 1)
            }
            dayholder?.add(Calendar.DATE, 1)
        }

        internal var dayholder: Calendar? = null

        fun getCurrentUser() {

            val auth = FirebaseAuth.getInstance()
            val userdb = FirebaseDatabase.getInstance().getReference("users")
            val query = userdb.orderByKey().equalTo(auth.currentUser!!.uid)
            query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val iterator = dataSnapshot.children.iterator()
                    while (iterator.hasNext()) {
                        val dataSnapshot1 = iterator.next()

                        BaseApplication.user = dataSnapshot1.getValue(User::class.java)

                    }
                    //getAllReservations()


                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e("DataManager", databaseError.code.toString())
                    Log.e("DataManager", databaseError.message)
                    Log.e("DataManager", databaseError.details)

                }
            })


        }




        fun  setList(arrayToSet: ArrayList<DateReservation>) {
            masterTakenList=arrayToSet
        }


    }



}



