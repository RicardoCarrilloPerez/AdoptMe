package com.example.adoptme

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class ViewAdoptantesActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var btnBackToMain: Button
    private lateinit var firestore: FirebaseFirestore
    private lateinit var adoptanteList: MutableList<Adoptante>
    private lateinit var adapter: AdoptanteAdapter
    private var firestoreListener: ListenerRegistration? = null
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_adoptantes)

        listView = findViewById(R.id.listView)
        btnBackToMain = findViewById(R.id.btn_back_to_main)

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

        // Inicializa Firestore
        firestore = FirebaseFirestore.getInstance()
        adoptanteList = mutableListOf()

        // Configura el adaptador
        adapter = AdoptanteAdapter(this, R.layout.list_item, adoptanteList)
        listView.adapter = adapter

        // Escucha los cambios en Firestore
        firestoreListener = firestore.collection("adoptantes")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Toast.makeText(this, "Error al cargar los datos", Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }

                adoptanteList.clear()
                for (doc in snapshots!!) {
                    val adoptante = doc.toObject(Adoptante::class.java)
                    adoptanteList.add(adoptante)
                }
                adapter.notifyDataSetChanged()
            }

        btnBackToMain.setOnClickListener {
            finish() // Termina la actividad actual para volver a la actividad anterior
        }
    }

    // Función para manejar la selección del menú del DrawerLayout
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        firestoreListener?.remove()
    }

    // Clase de datos para Adoptante
    data class Adoptante(val name: String = "", val address: String = "", val phone: String = "", val animalName: String = "")

    // Clase Adaptador para Adoptante
    class AdoptanteAdapter(context: Context, private val resource: Int, private val items: List<Adoptante>) :
        ArrayAdapter<Adoptante>(context, resource, items) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)
            val adoptante = items[position]

            view.findViewById<TextView>(R.id.tv_name).text = adoptante.name
            view.findViewById<TextView>(R.id.tv_address).text = adoptante.address
            view.findViewById<TextView>(R.id.tv_phone).text = adoptante.phone
            view.findViewById<TextView>(R.id.tv_animalName).text = adoptante.animalName

            return view
        }
    }

}
