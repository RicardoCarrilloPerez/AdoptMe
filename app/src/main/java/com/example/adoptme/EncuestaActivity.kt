package com.example.adoptme

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class EncuestaActivity : AppCompatActivity() {

    private lateinit var spinnerEspacio: Spinner
    private lateinit var spinnerTiempo: Spinner
    private lateinit var rgTipoAnimal: RadioGroup
    private lateinit var spinnerNivelActividad: Spinner
    private lateinit var rgNinos: RadioGroup
    private lateinit var spinnerPresupuesto: Spinner
    private lateinit var rgNecesidadesEspeciales: RadioGroup
    private lateinit var btnEnviarEncuesta: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encuesta)

        spinnerEspacio = findViewById(R.id.spinnerEspacio)
        spinnerTiempo = findViewById(R.id.spinnerTiempo)
        rgTipoAnimal = findViewById(R.id.rgTipoAnimal)
        spinnerNivelActividad = findViewById(R.id.spinnerNivelActividad)
        rgNinos = findViewById(R.id.rgNinos)
        spinnerPresupuesto = findViewById(R.id.spinnerPresupuesto)
        rgNecesidadesEspeciales = findViewById(R.id.rgNecesidadesEspeciales)
        btnEnviarEncuesta = findViewById(R.id.btnEnviarEncuesta)

        btnEnviarEncuesta.setOnClickListener {
            val tipoAnimal = determineTipoAnimal()
            redirectToAnimalList(tipoAnimal)
        }
    }

    private fun determineTipoAnimal(): String {
        val espacio = spinnerEspacio.selectedItemPosition
        val tiempo = spinnerTiempo.selectedItemPosition
        val tipoAnimalPreferencia = when (rgTipoAnimal.checkedRadioButtonId) {
            R.id.rbPerro -> "Perro"
            R.id.rbGato -> "Gato"
            R.id.rbConejo -> "Conejo"
            else -> "SinPreferencia"
        }
        val nivelActividad = spinnerNivelActividad.selectedItemPosition
        val hayNinos = rgNinos.checkedRadioButtonId == R.id.rbNinosSi
        val presupuesto = spinnerPresupuesto.selectedItemPosition
        val necesidadesEspeciales = rgNecesidadesEspeciales.checkedRadioButtonId == R.id.rbNecesidadesEspecialesSi

        return when {
            tipoAnimalPreferencia == "Perro" -> "Perro"
            tipoAnimalPreferencia == "Gato" -> "Gato"
            tipoAnimalPreferencia == "Conejo" -> "Conejo"
            espacio == 2 && nivelActividad == 0 && tiempo > 0 -> "Perro"
            espacio == 1 && nivelActividad in 1..2 && tiempo > 0 -> "Gato"
            espacio == 0 && nivelActividad == 2 -> "Conejo"
            else -> "SinPreferencia"
        }
    }

    private fun redirectToAnimalList(tipoAnimal: String) {
        val intent = Intent(this, AnimalListActivity::class.java)
        intent.putExtra("TIPO_ANIMAL", tipoAnimal)
        startActivity(intent)
    }
}
