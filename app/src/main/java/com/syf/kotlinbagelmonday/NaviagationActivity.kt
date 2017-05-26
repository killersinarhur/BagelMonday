package com.syf.kotlinbagelmonday

import android.content.Context
import android.content.Intent
import android.support.design.widget.TabLayout

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.os.Bundle
import android.support.annotation.NonNull
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.syf.kotlinbagelmonday.authentucation.LoginActivity
import com.syf.kotlinbagelmonday.fragment.MainActivity

import com.syf.kotlinbagelmonday.fragment.ReservedByMe
import com.syf.kotlinbagelmonday.fragment.ReservedDate
import com.syf.kotlinbagelmonday.model.DateReservation
import com.syf.kotlinbagelmonday.model.User


class NaviagationActivity : AppCompatActivity() {

    internal val auth:FirebaseAuth= FirebaseAuth.getInstance()
    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * [FragmentPagerAdapter] derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null


    /**
     * The [ViewPager] that will host the section contents.
     */
    private var mViewPager: ViewPager? = null


    private var authListener: FirebaseAuth.AuthStateListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_naviagation)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container) as ViewPager
        mViewPager!!.adapter = mSectionsPagerAdapter

        val tabLayout = findViewById(R.id.tabs) as TabLayout
        tabLayout.setupWithViewPager(mViewPager)


       authListener= FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user == null) {
                // user auth state is changed - user is null
                // launch login activity
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        }

    public override fun onStart() {
        super.onStart()
        auth.addAuthStateListener(authListener!!)
    }

    public override fun onStop() {
        super.onStop()
        if (authListener != null) {
            auth.removeAuthStateListener(authListener!!)
        }
    }

    override fun onResume() {
        super.onResume()
        val changeOccured=intent.getBooleanExtra("changeMade",false)
        if (changeOccured){
            Toast.makeText(this,"Thanks for Volunteering", Toast.LENGTH_LONG).show()

        }
        intent.removeExtra("changeMade")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_naviagation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


//        if (id == R.id.action_settings) {
//            return true
//        }

        when(id){
            R.id.log_out -> signout()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun signout() {
        auth.signOut()

    }
    internal var user: User? = null









        /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            var frag:Fragment?= null

            when (position) {
                0 -> frag= MainActivity.newInstance()
                1 -> frag= ReservedDate.newInstance()
                2 -> frag= ReservedByMe.newInstance()
            }
            return frag
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return "Available Dates"
                1 -> return "Taken Dates"
                2 -> return "Reserved By You"
            }
            return null
        }
    }

}



