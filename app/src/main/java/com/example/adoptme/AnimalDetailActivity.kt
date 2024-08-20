package com.example.adoptme

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.squareup.picasso.Picasso

class AnimalDetailActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    private lateinit var imageViewAnimal: ImageView
    private lateinit var textViewAnimalName: TextView
    private lateinit var textViewAnimalType: TextView
    private lateinit var textViewAnimalDetails: TextView
    private lateinit var textViewAnimalPersonality: TextView
    private lateinit var textViewAnimalAdditional: TextView
    private lateinit var textViewAnimalLocation: TextView
    private lateinit var buttonAdoptar: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_detail)

        imageViewAnimal = findViewById(R.id.imageViewAnimal)
        textViewAnimalName = findViewById(R.id.textViewAnimalName)
        textViewAnimalType = findViewById(R.id.textViewAnimalType)
        textViewAnimalDetails = findViewById(R.id.textViewAnimalDetails)
        textViewAnimalPersonality = findViewById(R.id.textViewAnimalPersonality)
        textViewAnimalAdditional = findViewById(R.id.textViewAnimalAdditional)
        textViewAnimalLocation = findViewById(R.id.textViewAnimalLocation)
        buttonAdoptar = findViewById(R.id.buttonAdoptar)

        val animal = intent.getSerializableExtra("ANIMAL_DETAILS") as Animal

        // Mostrar los datos del animal
        Picasso.get()
            .load(animal.imagen)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .into(imageViewAnimal)

        textViewAnimalName.text = animal.nombre
        textViewAnimalType.text = "${animal.tipo}, ${animal.genero}, ${animal.edad}"
        textViewAnimalDetails.text = Html.fromHtml(animal.desc_fisica, Html.FROM_HTML_MODE_COMPACT)
        textViewAnimalPersonality.text =
            Html.fromHtml(animal.desc_personalidad, Html.FROM_HTML_MODE_COMPACT)
        textViewAnimalAdditional.text =
            Html.fromHtml(animal.desc_adicional, Html.FROM_HTML_MODE_COMPACT)
        textViewAnimalLocation.text = "Ubicación: ${animal.comuna}, ${animal.region}"

        // Configurar el botón "Adoptar"
        buttonAdoptar.setOnClickListener {
            val intent = Intent(this, Adoptantes::class.java)
            intent.putExtra("ANIMAL_NAME", animal.nombre)
            startActivity(intent)
        }

        btnBack = findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            finish() // Termina la actividad actual y regresa a la anterior
        }


    }

    }




