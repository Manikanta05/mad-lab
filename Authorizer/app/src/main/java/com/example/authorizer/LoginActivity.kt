package com.example.authorizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import kotlinx.android.synthetic.main.login.*
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        supportActionBar?.setTitle("Log In")

        val db = Room.databaseBuilder(applicationContext, AppDB::class.java, "app-database").allowMainThreadQueries().build()
        val userDao = db.userDao()

        var count = 0

        logInButton.setOnClickListener {
            try{
                val tempUser = userDao.getUser(login_username.text.toString())
                if(tempUser.password.equals(login_password.text.toString())){
                    Toast.makeText(baseContext,"Login successful",Toast.LENGTH_SHORT).show()
                    count = 0
                }
                else{
                    if(count<2){
                        Toast.makeText(baseContext,"Invalid Username Or Password",Toast.LENGTH_SHORT).show()
                        count++
                    }
                    else{
                        Toast.makeText(baseContext,"Failed, Log in disabled",Toast.LENGTH_SHORT).show()
                        logInButton.isEnabled = false
                    }
                }
            }
            catch (e: Exception) {
                Toast.makeText(baseContext, "Invalid Username", Toast.LENGTH_SHORT).show()
            }
        }

        login_to_signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}