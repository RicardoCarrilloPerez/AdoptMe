package com.example.adoptme

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.adoptme.R
import com.google.android.material.navigation.NavigationView

class Perritos_Activity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    private lateinit var btnAdoptar: Button
    private var selectedCard: CardView? = null
    private lateinit var cardView1: CardView
    private lateinit var cardView2: CardView
    private lateinit var cardView3: CardView
    private lateinit var cardView4: CardView

    private val cardData = mapOf(
        R.id.cardView1 to R.drawable.peque1,
        R.id.cardView2 to R.drawable.peque2,
        R.id.cardView3 to R.drawable.peque3,
        R.id.cardView4 to R.drawable.peque4
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perritos)

        btnAdoptar = findViewById(R.id.btnAdoptar)

        cardView1 = findViewById(R.id.cardView1)
        cardView2 = findViewById(R.id.cardView2)
        cardView3 = findViewById(R.id.cardView3)
        cardView4 = findViewById(R.id.cardView4)

        val cardClickListener = View.OnClickListener { view ->
            val clickedCard = view as CardView

            if (clickedCard == selectedCard) {
                selectedCard?.setCardBackgroundColor(resources.getColor(android.R.color.white))
                selectedCard = null
                btnAdoptar.isEnabled = false
            } else {
                selectedCard?.setCardBackgroundColor(resources.getColor(android.R.color.white))
                selectedCard = clickedCard
                selectedCard?.setCardBackgroundColor(resources.getColor(R.color.selected))
                btnAdoptar.isEnabled = true
            }
        }

        cardView1.setOnClickListener(cardClickListener)
        cardView2.setOnClickListener(cardClickListener)
        cardView3.setOnClickListener(cardClickListener)
        cardView4.setOnClickListener(cardClickListener)

        btnAdoptar.setOnClickListener {
            selectedCard?.let { cardView ->
                val imageResId = cardData[cardView.id] ?: R.drawable.perrito1
                val intent = Intent(this, CardMascota_Activity::class.java).apply {
                    putExtra("imageResId", imageResId)
                    // Aquí puedes añadir más datos como nombre, raza, etc.
                }
                startActivity(intent)
            }
        }

        val drawerLayout : DrawerLayout =
            findViewById(R.id.drawerLayout)
        val navView : NavigationView =
            findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout,
            R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> startActivity(Intent(this,
                    Perritos_Activity::class.java))
                R.id.nav_adopciones -> startActivity(Intent(this,
                    p_adopciones::class.java))
                R.id.nav_adoptantes -> startActivity(Intent(this,
                    Adoptantes::class.java))
                R.id.nav_refugios -> startActivity(Intent( this,
                    refugios::class.java))
                R.id.nav_settings ->
                    Toast.makeText(applicationContext, "Clicked Setting",
                        Toast.LENGTH_SHORT).show()
                R.id.nav_share ->
                    Toast.makeText(applicationContext, "Clicked Share",
                        Toast.LENGTH_SHORT).show()
            }
            true
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}

