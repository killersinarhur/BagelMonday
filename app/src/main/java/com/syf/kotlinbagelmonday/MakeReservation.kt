package com.syf.kotlinbagelmonday

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.syf.kotlinbagelmonday.model.DateReservation
import com.syf.kotlinbagelmonday.model.PlaceholderDate
import com.syf.kotlinbagelmonday.model.User

class MakeReservation : AppCompatActivity() {

    internal var date:Int?=null
    internal var datez: PlaceholderDate?= null
    internal var user:User?=null
    var database=FirebaseDatabase.getInstance()
    var  auth = FirebaseAuth.getInstance()
    var firstNameField:EditText?= null
    var lastNameField:EditText?= null
    var phoneField:EditText?= null
    var emailField:EditText?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_reservation)
        date= intent.getIntExtra("position",0)
        datez=BaseApplication.dates!![date as Int]
        user=BaseApplication.user
        firstNameField= findViewById(R.id.first_name_field) as EditText
        lastNameField= findViewById(R.id.last_name_field) as EditText
        phoneField= findViewById(R.id.contact_field) as EditText
        emailField= findViewById(R.id.email_field) as EditText




        val dateResvered= findViewById(R.id.date_being_reserved) as TextView
        dateResvered.setText(datez.toString())
        val button= findViewById(R.id.btn_submit) as Button
        button.setOnClickListener(View.OnClickListener
        { checkOrCreateUser()
            makeReservation()
        })

        prePopulateInfo()
    }

    private fun checkOrCreateUser() {
        val newUser=User(firstNameField?.text.toString(),
                lastNameField?.text.toString(),
                phoneField?.text.toString())

        if (user==null) {
            val databaseStuff = database.getReference("users")
            databaseStuff.child(auth.currentUser?.uid).setValue(newUser)
            user=newUser
        }
    }

    private fun makeReservation() {
        val reservation= DateReservation(datez as PlaceholderDate, user as User)

        val myRef1 = database.getReference("reservation")
        myRef1.child(auth.currentUser!!.uid).child().setValue(reservation)

       // BaseApplication.organizeList()
        val intent = Intent(this, NaviagationActivity::class.java)
        intent.putExtra("changeMade", true)
        startActivity(intent)




    }


    private fun prePopulateInfo() {

        firstNameField?.setText(user?.firstName)
        lastNameField?.setText(user?.lastName)
        phoneField?.setText(user?.phoneNumber)
        emailField?.setText(auth.currentUser!!.email)
    }


}
