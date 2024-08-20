package com.example.adoptme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AnimalAdapter(private val animales: List<Animal>, private val onItemClick: (Animal) -> Unit) :
    RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    class AnimalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewAnimal: ImageView = view.findViewById(R.id.imageViewAnimal)
        val textViewAnimalName: TextView = view.findViewById(R.id.textViewAnimalName)
        val textViewAnimalDetails: TextView = view.findViewById(R.id.textViewAnimalDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animales[position]

        holder.textViewAnimalName.text = animal.nombre
        holder.textViewAnimalDetails.text = "${animal.tipo}, ${animal.edad}"

        // Cargar la imagen con Picasso
        Picasso.get()
            .load(animal.imagen)
            .placeholder(R.drawable.adop222) // Imagen de placeholder mientras carga
            .error(R.drawable.ic_stat_name) // Imagen de error si falla la carga
            .into(holder.imageViewAnimal)

        holder.itemView.setOnClickListener {
            onItemClick(animal)
        }
    }

    override fun getItemCount() = animales.size
}
