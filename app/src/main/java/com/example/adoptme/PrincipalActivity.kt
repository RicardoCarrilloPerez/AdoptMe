package com.example.adoptme

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PrincipalActivity : AppCompatActivity() {

    private lateinit var imageViewAnimal: ImageView
    private lateinit var textViewAnimalName: TextView
    private lateinit var textViewAnimalType: TextView
    private lateinit var textViewAnimalDetails: TextView
    private lateinit var textViewAnimalPersonality: TextView
    private lateinit var textViewAnimalAdditional: TextView
    private lateinit var textViewAnimalLocation: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_detail)

        imageViewAnimal = findViewById(R.id.imageViewAnimal)
        textViewAnimalName = findViewById(R.id.textViewAnimalName)
        textViewAnimalType = findViewById(R.id.textViewAnimalType)
        textViewAnimalDetails = findViewById(R.id.textViewAnimalDetails)
        textViewAnimalPersonality = findViewById(R.id.textViewAnimalPersonality)
        textViewAnimalAdditional = findViewById(R.id.textViewAnimalAdditional)
        textViewAnimalLocation = findViewById(R.id.textViewAnimalLocation)

        fetchAnimalDetails()
    }

    private fun fetchAnimalDetails() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClientH.api.getAnimales()
                val animal = response.data.firstOrNull() // Obtener el primer animal de la lista
                animal?.let {
                    runOnUiThread {
                        // Cargar la imagen con Picasso
                        Picasso.get()
                            .load(animal.imagen)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_foreground)
                            .into(imageViewAnimal)

                        // Establecer el resto de la información en las vistas correspondientes
                        textViewAnimalName.text = animal.nombre
                        textViewAnimalType.text = "${animal.tipo}, ${animal.genero}, ${animal.edad}"

                        // Mostrar el texto HTML o eliminar etiquetas
                        textViewAnimalDetails.text = Html.fromHtml(animal.desc_fisica, Html.FROM_HTML_MODE_COMPACT)
                        textViewAnimalPersonality.text = Html.fromHtml(animal.desc_personalidad, Html.FROM_HTML_MODE_COMPACT)
                        textViewAnimalAdditional.text = Html.fromHtml(animal.desc_adicional, Html.FROM_HTML_MODE_COMPACT)

                        // O, si prefieres texto limpio sin HTML:
                        // textViewAnimalDetails.text = stripHtmlTags(animal.desc_fisica)
                        // textViewAnimalPersonality.text = stripHtmlTags(animal.desc_personalidad)
                        // textViewAnimalAdditional.text = stripHtmlTags(animal.desc_adicional)

                        textViewAnimalLocation.text = "Ubicación: ${animal.comuna}, ${animal.region}"
                    }
                }
            } catch (e: Exception) {
                // Manejar el error
            }
        }
    }
}
