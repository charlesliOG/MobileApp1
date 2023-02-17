package com.charlesli.app1

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap

class DisplayActivity : AppCompatActivity() {
    var mTvFirstName: TextView? = null
    var mTvMiddleName: TextView? = null
    var mTvLastName: TextView? = null
    var mTvLogin: TextView? = null
    var mIvThumbnail: ImageView? = null

    private var mFirstName: String? = null
    private var mMiddleName: String? = null
    private var mLastName: String? = null
    private var mLogin: String? = null
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        mFirstName = mTvFirstName!!.text.toString()
        mMiddleName = mTvMiddleName!!.text.toString()
        mLastName = mTvLastName!!.text.toString()
        mLogin = mTvLogin!!.text.toString()

        outState.putString("FN_TEXT", mFirstName)
        outState.putString("MN_TEXT", mMiddleName)
        outState.putString("LN_TEXT", mLastName)
        outState.putString("LO_TEXT", mLogin)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        mTvFirstName!!.text = savedInstanceState.getString("FN_TEXT")
        mTvMiddleName!!.text = savedInstanceState.getString("MN_TEXT")
        mTvLastName!!.text = savedInstanceState.getString("LN_TEXT")
        mTvLogin!!.text = savedInstanceState.getString("LO_TEXT")

    }
}