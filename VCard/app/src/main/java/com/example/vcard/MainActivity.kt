package com.example.vcard

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var img: Uri? = null
    private var pickImg = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setViews()
    }

    //Button handler functions
    private fun setViews() {
        logoBtn.setOnClickListener{pickImage()}
        genBtn.setOnClickListener{setTextViews()}
    }

    //Function to set text views
    private fun setTextViews(){
        val cName = cName.text
        val pName = pName.getText()
        val pJob = pJob.getText()
        val pPhone = pPhone.getText()
        val pAddress = pAddress.getText()
        val pMail = pMail.getText()

        cNameView.text = cName
        pNameView.text = pName
        pJobView.text = pJob
        pAddressView.text = pAddress
        pMailView.text = pMail
        pPhoneView.text = pPhone
        lineView.setBackgroundColor(Color.BLACK)
        cLogoView.setImageURI(img)
    }

    //Function to select picture
    private fun pickImage(){
        val pic = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(pic, pickImg)
    }

    //Function to apply picture
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == pickImg){
            img = data?.data
        }
    }
}