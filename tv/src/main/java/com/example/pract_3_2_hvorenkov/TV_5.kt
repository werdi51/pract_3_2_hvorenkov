package com.example.pract_3_2_hvorenkov

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TV_5 : Activity() {

    private lateinit var rec: RecyclerView
    private lateinit var adapter: adapter
    private lateinit var genreSpinner: Spinner
    private lateinit var searchInput: EditText
    private lateinit var searchButton: AppCompatButton

    private val genres = arrayOf(
        "action", "comedy", "drama", "horror", "romance",
        "thriller", "adventure", "fantasy", "animation"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv5)

        initViews()
        setupSpinner()
        setupRecyclerView()
        setupSearchButton()

        loadMovies("action", "")
    }

    private fun initViews() {
        rec = findViewById(R.id.recyclers)
        genreSpinner = findViewById(R.id.genres)
        searchInput = findViewById(R.id.input)
        searchButton = findViewById(R.id.search_button)
    }

    private fun setupSpinner() {
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genres)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genreSpinner.adapter = spinnerAdapter
    }

    private fun setupRecyclerView() {
        rec.layoutManager = GridLayoutManager(this, 3)
        adapter = adapter(this, emptyList())
        rec.adapter = adapter
    }

    private fun setupSearchButton() {
        searchButton.setOnClickListener {
            val selectedGenre = genreSpinner.selectedItem.toString()
            val searchText = searchInput.text.toString().trim()

            if (searchText.isNotEmpty()) {
                loadMovies("", searchText)
            } else {
                loadMovies(selectedGenre, "")
            }
        }
    }

    private fun loadMovies(genre: String, searchQuery: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val query = if (searchQuery.isNotEmpty()) searchQuery else genre

                val response = RetrofitRealization.api.searchMovies(query)

                withContext(Dispatchers.Main) {
                    if (response.Response == "True") {
                        val movies = response.Search ?: emptyList()
                        adapter.movies = movies
                        adapter.notifyDataSetChanged()
                        Toast.makeText(
                            this@TV_5,
                            "Найдено ${movies.size} фильмов",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@TV_5,
                            "Фильмы не найдены",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                Log.e("TV_5", "Error: ${e.message}", e)
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@TV_5,
                        "Ошибка загрузки: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}