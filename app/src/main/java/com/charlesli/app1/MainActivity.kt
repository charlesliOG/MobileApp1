package com.charlesli.app1

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private var mFirstName: String? = null
    private var mMiddleName: String? = null
    private var mLastName: String? = null


    private var mTvFirstName: TextView? = null
    private var mTvMiddleName: TextView? = null
    private var mTvLastName: TextView? = null
    private var mButtonSubmit: Button? = null
    private var mButtonCamera: Button? = null

    private var mThumbnailImage: Bitmap? = null

    private var mDisplayIntent: Intent? = null

    private var mIvPic: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mButtonSubmit = findViewById<View>(R.id.button_submit) as Button
        mButtonCamera = findViewById<View>(R.id.button_pic) as Button

        mButtonSubmit!!.setOnClickListener(this)
        mButtonCamera!!.setOnClickListener(this)

        mDisplayIntent = Intent(this, DisplayActivity::class.java)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_submit -> {

                //First, get the string from the EditText
                mTvFirstName = findViewById<View>(R.id.et_fname) as EditText
                mTvMiddleName = findViewById<View>(R.id.et_mname) as EditText
                mTvLastName = findViewById<View>(R.id.et_lname) as EditText
                mFirstName = mTvFirstName!!.text.toString()
                mMiddleName = mTvMiddleName!!.text.toString()
                mLastName = mTvLastName!!.text.toString()

                //Check if the EditText string is empty
                if (mFirstName.isNullOrBlank()) {
                    //Complain that there's no text
                    Toast.makeText(this@MainActivity, "Enter a first name!", Toast.LENGTH_SHORT)
                        .show()
                }

                else if (mMiddleName.isNullOrBlank()){

                    Toast.makeText(this@MainActivity, "Enter a middle name!", Toast.LENGTH_SHORT).show()

                }
                else if (mLastName.isNullOrBlank()){

                    Toast.makeText(this@MainActivity, "Enter a last name!", Toast.LENGTH_SHORT).show()

                }
                else
                {
                    mDisplayIntent!!.putExtra("FN_DATA", mFirstName)
                    mDisplayIntent!!.putExtra("MN_DATA", mMiddleName)
                    mDisplayIntent!!.putExtra("LN_DATA", mLastName)
                    startActivity(mDisplayIntent) //explicit intent
                }
            }
            R.id.button_pic -> {

                //The button press should open a camera
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try{
                    cameraActivity.launch(cameraIntent)
                }catch(ex: ActivityNotFoundException){
                    //Do error handling here
                }
            }
        }
    }
    private val cameraActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == RESULT_OK) {
            mIvPic = findViewById<View>(R.id.iv_pic) as ImageView
            //val extras = result.data!!.extras
            //val thumbnailImage = extras!!["data"] as Bitmap?

            if (Build.VERSION.SDK_INT >= 33) {
                val thumbnailImage = result.data!!.getParcelableExtra("data", Bitmap::class.java)
                mIvPic!!.setImageBitmap(thumbnailImage)
            }
            else{
                val thumbnailImage = result.data!!.getParcelableExtra<Bitmap>("data")
                mIvPic!!.setImageBitmap(thumbnailImage)
            }


        }
    }
}