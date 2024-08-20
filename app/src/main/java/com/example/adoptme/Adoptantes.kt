package com.example.adoptme

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore

class Adoptantes : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etAddress: EditText
    private lateinit var etPhone: EditText
    private lateinit var btnSubmit: Button
    private lateinit var btnViewAdoptantes: Button
    private lateinit var db: FirebaseFirestore
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var btnBack: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_adoptantes)

        etName = findViewById(R.id.et_name)
        etAddress = findViewById(R.id.et_address)
        etPhone = findViewById(R.id.et_phone)
        btnSubmit = findViewById(R.id.btn_submit)
        btnViewAdoptantes = findViewById(R.id.btn_view_adoptantes)
        btnBack = findViewById(R.id.btn_back)
        var databaseHelper = DatabaseHelper(this)

        val animalName = intent.getStringExtra("ANIMAL_NAME") ?: ""

        // Inicializa Firestore
        db = FirebaseFirestore.getInstance()

        // Configuración del DrawerLayout y NavigationView
        drawerLayout = findViewById(R.id.drawerLayout)
        navView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> startActivity(Intent(this, Perritos_Activity::class.java))
                R.id.nav_adopciones -> startActivity(Intent(this, p_adopciones::class.java))
                R.id.nav_adoptantes -> startActivity(Intent(this, ViewAdoptantesActivity::class.java))
                R.id.nav_refugios -> startActivity(Intent(this, refugios::class.java))
                R.id.nav_settings -> Toast.makeText(applicationContext, "Clicked Setting", Toast.LENGTH_SHORT).show()
                R.id.nav_share -> Toast.makeText(applicationContext, "Clicked Share", Toast.LENGTH_SHORT).show()
            }
            true
        }

        btnSubmit.setOnClickListener {
            val name = etName.text.toString()
            val address = etAddress.text.toString()
            val phone = etPhone.text.toString()

            if (name.isNotEmpty() && address.isNotEmpty() && phone.isNotEmpty() && phone.length == 10 && phone.all { it.isDigit() }) {

                // Consulta para verificar si el nombre del animal ya existe
                db.collection("adoptantes")
                    .whereEqualTo("animalName", animalName)
                    .get()
                    .addOnSuccessListener { result ->
                        if (result.isEmpty) {
                            // Si el nombre del animal no existe, guarda los datos
                            val adoptante = Adoptante(name, address, phone, animalName)

                            db.collection("adoptantes")
                                .add(adoptante)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
                                    etName.text.clear()
                                    etAddress.text.clear()
                                    etPhone.text.clear()
                                }
                                .addOnFailureListener {
                                    Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            // Si el nombre del animal ya existe, muestra un mensaje
                            Toast.makeText(this, "Este animal ya ha sido adoptado", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Error al verificar el animal", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        btnViewAdoptantes.setOnClickListener {
            val intent = Intent(this, ViewAdoptantesActivity::class.java)
            startActivity(intent)
        }

        btnBack.setOnClickListener{
            finish()
        }
    }


    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    data class Adoptante(val name: String = "", val address: String = "", val phone: String = "", val animalName: String)
}
