package com.example.adoptme

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class p_adopciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_padopciones)

        val resR = findViewById<Button>(R.id.btnregresar_1)

        resR.setOnClickListener {
            val intent = Intent(this, Perritos_Activity::class.java)
            startActivity(intent)
        }

    }
}