package com.syf.kotlinbagelmonday.authentucation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.syf.kotlinbagelmonday.BaseApplication
import com.syf.kotlinbagelmonday.NaviagationActivity

import com.syf.kotlinbagelmonday.R
import com.syf.kotlinbagelmonday.model.User

class CreateProfileActivity : AppCompatActivity() {

    val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)
        val fNameField= findViewById(R.id.et_first_name) as EditText
        val lNameField= findViewById(R.id.et_last_name) as EditText
        val pNumberField= findViewById(R.id.et_phone_number) as EditText
        val buttonSelected= findViewById(R.id.create_profile_btn) as Button
        buttonSelected.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {

                val newUser=User(fNameField.text.toString(),
                        lNameField.text.toString(),
                        pNumberField.text.toString())
                BaseApplication.user=newUser
                createNewUser(newUser)
            }
        })

    }

    private fun createNewUser(newUser: User) {
        val userdb = FirebaseDatabase.getInstance().getReference("users")
        userdb.child(auth.currentUser!!.uid).setValue(newUser)
        val intent= Intent(this@CreateProfileActivity,NaviagationActivity::class.java)
        startActivity(intent)
    }
}
