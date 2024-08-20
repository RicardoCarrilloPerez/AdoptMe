package com.example.adoptme

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnimalListActivity : AppCompatActivity() {

    private lateinit var recyclerViewAnimals: RecyclerView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_list)

        // Configuración del RecyclerView
        recyclerViewAnimals = findViewById(R.id.recyclerViewAnimals)
        recyclerViewAnimals.layoutManager = LinearLayoutManager(this)

        // Obtener el tipo de animal desde el Intent
        val tipoAnimal = intent.getStringExtra("TIPO_ANIMAL") ?: "perro"
        fetchAnimalsByType(tipoAnimal)

        // Configuración del DrawerLayout y NavigationView
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)

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
    }

    // Función para manejar la selección del menú del DrawerLayout
    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // Función para obtener la lista de animales por tipo
    private fun fetchAnimalsByType(tipo: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClientH.api.getAnimalesByTipo(tipo)
                val animales = response.data
                runOnUiThread {
                    recyclerViewAnimals.adapter = AnimalListAdapter(animales) { animal ->
                        val intent = Intent(this@AnimalListActivity, AnimalDetailActivity::class.java)
                        intent.putExtra("ANIMAL_DETAILS", animal)
                        startActivity(intent)
                    }
                }
            } catch (e: Exception) {
                // Manejar el error
                runOnUiThread {
                    Toast.makeText(this@AnimalListActivity, "Error al cargar los animales", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}