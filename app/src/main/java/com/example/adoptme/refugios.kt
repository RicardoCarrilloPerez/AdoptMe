package com.example.adoptme

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class refugios : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    private lateinit var btninfo_refug: Button
    private var selectedCard: CardView? = null
    private lateinit var cardView_r1: CardView
    private lateinit var cardView_r2: CardView
    private lateinit var cardView_r3: CardView
    private lateinit var cardView_r4: CardView

    private val cardData = mapOf(
        R.id.cardView_r1 to R.drawable.refugio1,
        R.id.cardView_r2 to R.drawable.refugio2,
        R.id.cardView_r3 to R.drawable.refugio3,
        R.id.cardView_r4 to R.drawable.refugio4
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_refugios)

        btninfo_refug = findViewById(R.id.btninfo_refug)

        cardView_r1 = findViewById(R.id.cardView_r1)
        cardView_r2 = findViewById(R.id.cardView_r2)
        cardView_r3 = findViewById(R.id.cardView_r3)
        cardView_r4 = findViewById(R.id.cardView_r4)

        val cardClickListener = View.OnClickListener { view ->
            val clickedCard = view as CardView

            if (clickedCard == selectedCard) {
                selectedCard?.setCardBackgroundColor(resources.getColor(android.R.color.white))
                selectedCard = null
                btninfo_refug.isEnabled = false
            } else {
                selectedCard?.setCardBackgroundColor(resources.getColor(android.R.color.white))
                selectedCard = clickedCard
                selectedCard?.setCardBackgroundColor(resources.getColor(R.color.selected))
                btninfo_refug.isEnabled = true
            }
        }

        cardView_r1.setOnClickListener(cardClickListener)
        cardView_r2.setOnClickListener(cardClickListener)
        cardView_r3.setOnClickListener(cardClickListener)
        cardView_r4.setOnClickListener(cardClickListener)

        btninfo_refug.setOnClickListener {
            selectedCard?.let { cardView ->
                val imageResId = cardData[cardView.id] ?: R.drawable.refugio1
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
