@file:Suppress("UsePropertyAccessSyntax")

package com.example.calculator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var x = 0.0
    private var y = 0.0
    private var disp:String = ""
    private val sb = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        handleNumberButton()
        handleSignButton()
        clearScreen()
        equalButton()
    }

    private fun handleNumberButton(){
        one.setOnClickListener{ appendToDisp("1") }
        two.setOnClickListener{ appendToDisp("2") }
        three.setOnClickListener{ appendToDisp("3") }
        four.setOnClickListener{ appendToDisp("4") }
        five.setOnClickListener{ appendToDisp("5") }
        six.setOnClickListener{ appendToDisp("6") }
        seven.setOnClickListener{ appendToDisp("7") }
        eight.setOnClickListener{ appendToDisp("8") }
        nine.setOnClickListener{ appendToDisp("9") }
        zero.setOnClickListener{ appendToDisp("0") }
        dot.setOnClickListener{ appendToDisp(".") }
    }

    private fun handleSignButton(){
        plus.setOnClickListener{
            signView.setText("+")
            assignNumberForSign()
            performMath(y,x,"+")
        }
        minus.setOnClickListener{
            signView.setText("-")
            assignNumberForSign()
            performMath(y,x,"-")
        }
        slash.setOnClickListener{
            signView.setText("/")
            assignNumberForSign()
            performMath(y,x,"/")
        }
        star.setOnClickListener{
            signView.setText("*")
            assignNumberForSign()
            performMath(y,x,"*")
        }
    }

    private fun equalButton(){
        var a:Double
        var b:Double
        equal.setOnClickListener{
            if(secView.text=="" && numberView.text==""){
                numberView.text = "0.0"
                secView.text = "0.0"
            }
            if (secView.text == ""){
                a = 0.0
                b = numberView.text.toString().toDouble()
            }
            else {
                a = numberView.text.toString().toDouble()
                b = secView.text.toString().toDouble()
            }

            when (signView.text) {
                "+" -> numberView.text = (a+b).toString()
                "-" -> numberView.text = (b-a).toString()
                "*" -> numberView.text = (a*b).toString()
                "/" -> numberView.text = (b/a).toString()
            }

            secView.text = ""
        }
    }

    private fun performMath(a:Double, b:Double, s:String){
        when (signView.text) {
            "+" -> {
                numberView.text = (a+b).toString()
                secView.text = (a+b).toString()
            }
            "-" -> {
                numberView.text = (a-b).toString()
                secView.text = (a-b).toString()
            }
            "*" -> {
                numberView.text = (a*b).toString()
                secView.text = (a*b).toString()
            }
            "/" -> {
                numberView.text = (a/b).toString()
                secView.text = (a/b).toString()
            }
        }
    }

    private fun clearScreen(){
        clear.setOnClickListener{
            numberView.text = ""
            signView.text = ""
            secView.text = ""
            sb.clear()
        }
    }

    private fun appendToDisp(x:String){
        sb.append(disp).append(x)
        numberView.text = sb.toString()
    }

    private fun assignNumberForSign(){
        sb.clear()
        if(secView.text == ""){
            x = if(signView.text=="*" || signView.text=="/") 1.0 else 0.0
            secView.text = numberView.text
            y = secView.text.toString().toDouble()
        }
        else{
            x = numberView.text.toString().toDouble()
            y = secView.text.toString().toDouble()
        }
    }
}