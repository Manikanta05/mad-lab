package com.example.authorizer

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.room.Room
import java.util.regex.*
import kotlinx.android.synthetic.main.signup.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        supportActionBar?.setTitle("Sign Up")

        val db = Room.databaseBuilder(applicationContext, AppDB::class.java, "app-database").allowMainThreadQueries().build()
        val userDao = db.userDao()

        signUpButton.setOnClickListener {
            if(signUp_password.text.toString().length<8 && !isValidPassword(signUp_password.text.toString())){
                Toast.makeText(baseContext,"Invalid Password", Toast.LENGTH_LONG).show()
            }
            else{
                val newUser = User(signUp_username.text.toString(), signUp_password.text.toString())
                userDao.insert(newUser)
                Toast.makeText(baseContext,"Sign Up Sucessful", Toast.LENGTH_LONG).show()
            }
        }

        signUp_to_logIn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

        private fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)
        return matcher.matches() //0 or 1
    }
}