package com.vinay.coroutinejsonapp
/*
  Week 9
  MAPD711 -Samsung Android Application Development
  Created by Vinay
 */
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Downloading JSON data using a URL
        val url:URL? = try {
            URL("https://pastebin.com/raw/2bW31yqa")
        }catch (e: MalformedURLException){
            Log.d("Exception", e.toString())
            null
        }
        val btnJson: Button =findViewById(R.id.btnJson)
        val tvResults: TextView =findViewById(R.id.tvResults)

        // downloading json data from a given URL, then parse and display in UI
        btnJson.setOnClickListener {

            // coroutine to work with JSON Data
            GlobalScope.launch(Dispatchers.IO){
                url?.getString()?.apply {

                    // Parsing JSON using Dispatchers
                    withContext(Dispatchers.Default){
                        val list = parseJson(this@apply)

                        withContext(Dispatchers.Main){
                            tvResults.append("\n\nReading data from json....\n")

                            //assign the JSON values into a textview control
                            list?.forEach {
                                tvResults.append("\n${it.firstName}" +
                                        " ${it.lastName} ${it.age}")
                            }

                        }

                    }
                }
            }
        }
    }

    //Method to get JSON data from a URL as string format
    fun URL.getString(): String? {
        val stream = openStream()
        return try {
            val r = BufferedReader(InputStreamReader(stream))
            val result = StringBuilder()
            var line: String?
            while (r.readLine().also { line = it } != null) {
                result.append(line).appendln()
            }
            result.toString()
        }catch (e: IOException){
            e.toString()
        }
    }

    // String data conversion into JSON data then assign to a list
    fun parseJson(data:String):List<Student>?{
        val list = mutableListOf<Student>()

        try {
            val array = JSONObject(data).getJSONArray("students")
            for(i in 0 until array.length()){
                val obj = JSONObject(array[i].toString())
                val firstName = obj.getString("firstname")
                val lastName = obj.getString("lastname")
                val age = obj.getInt("age")
                list.add(Student(firstName,lastName,age))
            }
        }catch (e: JSONException){
            Log.d("Exception", e.toString())
        }

        return list
    }
}