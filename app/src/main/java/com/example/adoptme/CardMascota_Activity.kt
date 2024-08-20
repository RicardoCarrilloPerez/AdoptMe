package com.example.adoptme

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class CardMascota_Activity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cardmascota)

        val dogImage: ImageView = findViewById(R.id.dog_image)
        val dogName: TextView = findViewById(R.id.dog_name)
        val dogAge: TextView = findViewById(R.id.dog_age)
        val dogBreed: TextView = findViewById(R.id.dog_breed)
        val dogWeight: TextView = findViewById(R.id.dog_weight)
        val dogFavoriteFood: TextView = findViewById(R.id.dog_favorite_food)
        val dogDescription: TextView = findViewById(R.id.dog_description)
        val dogHobbies: TextView = findViewById(R.id.dog_hobbies)

        val imageResId = intent.getIntExtra("imageResId", R.drawable.peque1)
        val name = intent.getStringExtra("name") ?: "Nombre no disponible"
        val age = intent.getStringExtra("age") ?: "Edad no disponible"
        val breed = intent.getStringExtra("breed") ?: "Raza no disponible"
        val weight = intent.getStringExtra("weight") ?: "Peso no disponible"
        val favoriteFood = intent.getStringExtra("favoriteFood") ?: "Comida favorita no disponible"
        val description = intent.getStringExtra("description") ?: "Descripción no disponible"
        val hobbies = intent.getStringExtra("hobbies") ?: "Pasatiempos no disponibles"

        dogImage.setImageResource(imageResId)
        dogName.text = name
        dogAge.text = age
        dogBreed.text = breed
        dogWeight.text = weight
        dogFavoriteFood.text = favoriteFood
        dogDescription.text = description
        dogHobbies.text = hobbies


        val backButton: Button = findViewById(R.id.back)

        //
        backButton.setOnClickListener {

            onBackPressed()
        }
    }
}
