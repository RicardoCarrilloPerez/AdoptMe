package com.example.adoptme

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PerritosAdapter(private val perritos: List<Perrito>) : RecyclerView.Adapter<PerritosAdapter.PerritoViewHolder>() {

    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerritoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.perrito_item, parent, false)
        return PerritoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PerritoViewHolder, position: Int) {
        val perrito = perritos[position]
        holder.bind(perrito)

        // Configurar el listener para cuando se haga clic en la tarjeta
        holder.itemView.setOnClickListener {
            listener?.onItemClick(position)
            holder.btnAdoptar.isEnabled = true
        }

        // Configurar el listener para cuando se haga clic en el botón "Adoptar"
        holder.btnAdoptar.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, CardMascota_Activity::class.java).apply {
                putExtra("imageResId", perrito.imagen)
                putExtra("name", perrito.nombre)
                putExtra("age", perrito.edad)
                putExtra("breed", perrito.raza)
                putExtra("weight", perrito.peso)
                putExtra("favoriteFood", perrito.comidaFavorita)
                putExtra("description", perrito.descripcion)
                putExtra("hobbies", perrito.pasatiempos)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return perritos.size
    }

    class PerritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.dog_image)
        val nombreTextView: TextView = itemView.findViewById(R.id.dog_name)
        val razaTextView: TextView = itemView.findViewById(R.id.dog_breed)
        val descripcionTextView: TextView = itemView.findViewById(R.id.dog_description)
        val btnAdoptar: Button = itemView.findViewById(R.id.btnAdoptar)

        fun bind(perrito: Perrito) {
            imageView.setImageResource(perrito.imagen)
            nombreTextView.text = perrito.nombre
            razaTextView.text = perrito.raza
            descripcionTextView.text = perrito.descripcion
            btnAdoptar.isEnabled = true
        }
    }
}
