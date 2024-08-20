package com.example.adoptme

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {



    private val estadosViewModel: EstadosViewModel by viewModels()
    private lateinit var spinnerEstados: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)







        val resR = findViewById<Button>(R.id.comenzar_btn)

        resR.setOnClickListener {
            val intent = Intent(this, EncuestaActivity::class.java)
            startActivity(intent)
        }
    }
}

