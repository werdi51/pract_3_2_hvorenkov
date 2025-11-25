package com.example.pract_3_2_hvorenkov

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.view.LayoutInflater
import android.view.ViewGroup

class adapter(private val context: Context, var movies: List<Movie>) :
    RecyclerView.Adapter<adapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image_quests)
        val title: TextView = itemView.findViewById(R.id.title_quests)
        val desc: TextView = itemView.findViewById(R.id.descr)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quests, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        holder.title.text = movie.Title
        holder.desc.text = "Год: ${movie.Year}"


            Glide.with(context).load(movie.Poster).into(holder.image)

    }

    override fun getItemCount() = movies.size
}