package com.charlesli.app1

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DisplayActivity : AppCompatActivity() {
    var mTvFirstName: TextView? = null
    var mTvMiddleName: TextView? = null
    var mTvLastName: TextView? = null
    var mTvLogin: TextView? = null
    var mIvThumbnail: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        //Get the text views
        mTvFirstName = findViewById<View>(R.id.tv_fn_data) as TextView
        mTvMiddleName = findViewById<View>(R.id.tv_mn_data) as TextView
        mTvLastName = findViewById<View>(R.id.tv_ln_data) as TextView
        mTvLogin = findViewById<View>(R.id.tv_login) as TextView

        //Get the image view

        //Get the starter intent
        val receivedIntent = intent

        //Set the text views
        mTvFirstName!!.text = receivedIntent.getStringExtra("FN_DATA")
        mTvMiddleName!!.text = receivedIntent.getStringExtra("MN_DATA")
        mTvLastName!!.text = receivedIntent.getStringExtra("LN_DATA")
        mTvLogin!!.text = mTvFirstName!!.text.toString() + " " + mTvLastName!!.text.toString() +  " is logged in!"

        Toast.makeText(this@DisplayActivity, mTvFirstName!!.text.toString() + " " + mTvLastName!!.text.toString() +  " is logged in!", Toast.LENGTH_SHORT).show()
    }
}