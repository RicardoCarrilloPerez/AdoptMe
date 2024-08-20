package com.example.adoptme

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class Cuestionario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuestionario)

        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            if (isQuestionnaireComplete()) {
                val score = calculateScore()
                determineBreedType(score)
            } else {
                showIncompleteQuestionnaireDialog()
            }
        }
    }

    private fun isQuestionnaireComplete(): Boolean {
        val radioGroups = listOf(
            findViewById<RadioGroup>(R.id.radioGroupSpace),
            findViewById<RadioGroup>(R.id.radioGroupAccess),
            findViewById<RadioGroup>(R.id.radioGroupTime),
            findViewById<RadioGroup>(R.id.radioGroupActivity),
            findViewById<RadioGroup>(R.id.radioGroupExperience),
            findViewById<RadioGroup>(R.id.radioGroupHandle),
            findViewById<RadioGroup>(R.id.radioGroupPreferences),
            findViewById<RadioGroup>(R.id.radioGroupGuardian),
            findViewById<RadioGroup>(R.id.radioGroupTraining),
            findViewById<RadioGroup>(R.id.radioGroupEnergy),
            findViewById<RadioGroup>(R.id.radioGroupFamily),
            findViewById<RadioGroup>(R.id.radioGroupPets)
        )

        return radioGroups.all { it.checkedRadioButtonId != -1 }
    }

    private fun showIncompleteQuestionnaireDialog() {
        AlertDialog.Builder(this)
            .setTitle("Cuestionario Incompleto")
            .setMessage("Por favor, completa todas las preguntas antes de enviar.")
            .setPositiveButton("Aceptar") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun calculateScore(): Int {
        return listOf(
            findViewById<RadioGroup>(R.id.radioGroupSpace),
            findViewById<RadioGroup>(R.id.radioGroupAccess),
            findViewById<RadioGroup>(R.id.radioGroupTime),
            findViewById<RadioGroup>(R.id.radioGroupActivity),
            findViewById<RadioGroup>(R.id.radioGroupExperience),
            findViewById<RadioGroup>(R.id.radioGroupHandle),
            findViewById<RadioGroup>(R.id.radioGroupPreferences),
            findViewById<RadioGroup>(R.id.radioGroupGuardian),
            findViewById<RadioGroup>(R.id.radioGroupTraining),
            findViewById<RadioGroup>(R.id.radioGroupEnergy),
            findViewById<RadioGroup>(R.id.radioGroupFamily),
            findViewById<RadioGroup>(R.id.radioGroupPets)
        ).sumOf { getScoreFromRadioGroup(it) }
    }

    private fun getScoreFromRadioGroup(radioGroup: RadioGroup): Int {
        return when (radioGroup.checkedRadioButtonId) {
            R.id.radioOption1, R.id.radioOption4, R.id.radioOption7, R.id.radioOption10, R.id.radioOption13, R.id.radioOption16, R.id.radioOption19, R.id.radioOption22, R.id.radioOption25, R.id.radioOption28, R.id.radioOption31, R.id.radioOption34 -> 1
            R.id.radioOption2, R.id.radioOption5, R.id.radioOption8, R.id.radioOption11, R.id.radioOption14, R.id.radioOption17, R.id.radioOption20, R.id.radioOption23, R.id.radioOption26, R.id.radioOption29, R.id.radioOption32, R.id.radioOption35 -> 2
            R.id.radioOption3, R.id.radioOption6, R.id.radioOption9, R.id.radioOption12, R.id.radioOption15, R.id.radioOption18, R.id.radioOption21, R.id.radioOption24, R.id.radioOption27, R.id.radioOption30, R.id.radioOption33, R.id.radioOption36 -> 3
            else -> 0
        }
    }

    private fun determineBreedType(score: Int) {
        val intent = when {
            score <= 14 -> Intent(this, RazasPequeñas::class.java)
            score <= 18 -> Intent(this, RazasMedianas::class.java)
            else -> Intent(this, RazasGrandes::class.java)
        }
        startActivity(intent)
    }
}
