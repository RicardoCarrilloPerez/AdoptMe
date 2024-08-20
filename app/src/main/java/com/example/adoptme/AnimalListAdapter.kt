package com.example.adoptme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AnimalListAdapter(
    private var animales: List<Animal>, // Cambiado a MutableList
    private val onItemClick: (Animal) -> Unit
) : RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>() {

    class AnimalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewAnimal: ImageView = view.findViewById(R.id.imageViewAnimal)
        val textViewAnimalName: TextView = view.findViewById(R.id.textViewAnimalName)
        val textViewAnimalType: TextView = view.findViewById(R.id.textViewAnimalType)
        val textViewAnimalGender: TextView = view.findViewById(R.id.textViewAnimalGender)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_animal_name, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animales[position]

        // Cargar la imagen con Picasso
        Picasso.get()
            .load(animal.imagen)
            .placeholder(R.drawable.adop222)
            .error(R.drawable.ic_stat_name)
            .into(holder.imageViewAnimal)

        holder.textViewAnimalName.text = animal.nombre
        holder.textViewAnimalType.text = animal.tipo
        holder.textViewAnimalGender.text = animal.genero

        holder.itemView.setOnClickListener {
            onItemClick(animal)
        }
    }

    override fun getItemCount() = animales.size

    // Método para actualizar la lista de animales

}
