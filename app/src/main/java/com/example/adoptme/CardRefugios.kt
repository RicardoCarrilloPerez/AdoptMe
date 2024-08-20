package com.example.adoptme

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CardRefugios : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_refugios)

        val ref_image: ImageView = findViewById(R.id.ref_image)
        val ref_name: TextView = findViewById(R.id.ref_name)
        val ref_telefono: TextView = findViewById(R.id.ref_telefono)
        val ref_ubi: TextView = findViewById(R.id.ref_ubi)
        val ref_direc: TextView = findViewById(R.id.ref_direc)
        val ref_horarios: TextView = findViewById(R.id.ref_horarios)

        val imageResId = intent.getIntExtra("imageResId", R.drawable.refugio2)
        val name = intent.getStringExtra("name") ?: "Nombre no disponible"
        val age = intent.getStringExtra("age") ?: "Edad no disponible"
        val breed = intent.getStringExtra("breed") ?: "Raza no disponible"
        val weight = intent.getStringExtra("weight") ?: "Peso no disponible"
        val favoriteFood = intent.getStringExtra("favoriteFood") ?: "Comida favorita no disponible"

        ref_image.setImageResource(imageResId)
        ref_name.text = name
        ref_telefono.text = age
        ref_ubi.text = breed
        ref_direc.text = weight
        ref_horarios.text = favoriteFood

        val backButton: Button = findViewById(R.id.back)

        //
        backButton.setOnClickListener {

            onBackPressed()
        }

    }
}