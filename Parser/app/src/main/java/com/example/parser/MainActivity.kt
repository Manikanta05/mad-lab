package com.example.parser

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import javax.xml.parsers.DocumentBuilderFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        parse_xml.setOnClickListener{parseXML()}
        parse_json.setOnClickListener{parseJSON()}

    }

    @SuppressLint("SetTextI18n")
    fun parseXML(){
        data_type.text = "XML Data"
        try {
            val iStream = assets.open("myxml.xml")
            val builderFactory = DocumentBuilderFactory.newInstance()   //DOM Tree parse
            var docBuilder = builderFactory.newDocumentBuilder()        //DOM doc
            var doc = docBuilder.parse(iStream)                         //Final parse
            city_name.text = "City Name : " + doc.getElementsByTagName("City_Name").item(0).getFirstChild().getNodeValue()
            latitude.text = "Latitude : " + doc.getElementsByTagName("Latitude").item(0).getFirstChild().getNodeValue()
            longitude.text = "Longitude : " + doc.getElementsByTagName("Longitude").item(0).getFirstChild().getNodeValue()
            temprature.text = "Temperature : " + doc.getElementsByTagName("Temperature").item(0).getFirstChild().getNodeValue()
            humidity.text = "Humidity : " + doc.getElementsByTagName("Humidity").item(0).getFirstChild().getNodeValue()
        }
        catch (ex: IOException) { }
    }

    @SuppressLint("SetTextI18n")
    fun parseJSON(){
        data_type.text = "JSON Data"
        val obj = JSONObject(loadJSONFromAsset())
        city_name.text = "City Name : " + obj.getString("City Name")
        latitude.text = "Latitude : " + obj.getString("Latitude")
        longitude.text = "Longitude : " + obj.getString("Longitude")
        temprature.text = "Temperature : " + obj.getString("Temperature")
        humidity.text = "Humidity : " + obj.getString("Humidity")
    }

    private fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = assets.open("myjson.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}