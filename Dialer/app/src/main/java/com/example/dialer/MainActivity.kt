package com.example.dialer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)

        zero.setOnClickListener {
            pressButton("0", true)
        }

        one.setOnClickListener {
            pressButton("1", true)
        }

        two.setOnClickListener {
            pressButton("2", true)
        }

        three.setOnClickListener {
            pressButton("3", true)
        }

        four.setOnClickListener {
            pressButton("4", true)
        }

        five.setOnClickListener {
            pressButton("5", true)
        }

        six.setOnClickListener {
            pressButton("6", true)
        }

        seven.setOnClickListener {
            pressButton("7", true)
        }

        eight.setOnClickListener {
            pressButton("8", true)
        }

        nine.setOnClickListener {
            pressButton("9", true)
        }

        star.setOnClickListener {
            pressButton("*", true)
        }

        hash.setOnClickListener {
            pressButton("#", true)
        }


        clear.setOnClickListener {
            contact.text = ""
        }

        call.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "${contact.text}"))
            startActivity(intent)
        }

        save.setOnClickListener {
            val intent = Intent(ContactsContract.Intents.SHOW_OR_CREATE_CONTACT, Uri.parse("tel:" + contact.text))
            intent.putExtra(ContactsContract.Intents.EXTRA_FORCE_CREATE, true)
            startActivity(intent)
        }


    }

    private fun pressButton(string: String, clear: Boolean) {
        if (!clear) {
            contact.text = ""
        } else {
            contact.append(string)
        }
    }

}