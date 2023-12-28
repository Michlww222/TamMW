package com.android.wasik

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.wasik.adapters.StarshipAdapter
import com.android.wasik.data.Item
import com.android.wasik.data.Starship
import com.android.wasik.databinding.ActivityMainBinding
import com.android.wasik.service.StarService
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val starService: StarService = StarService.starService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchStarships()
    }

    private fun fetchStarships() {
        lifecycleScope.launch {
            try {
                val response = starService.getStarResponse()
                if (response.isSuccessful) {
                    val starships = response.body()?.results ?: emptyList()
                    updateRecyclerView(starships)
                } else {
                    // Handle error
                    Log.e("MainActivity", "Error: ${response.code()}")
                }
            } catch (e: Exception) {
                // Handle exception
                Log.e("MainActivity", "Exception: ${e.message}")
            }
        }
    }

    private fun updateRecyclerView(starships: List<Starship>) {
        val adapter = StarshipAdapter(starships) { selectedStarship ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putParcelableArrayListExtra("STARSHIP_LIST", ArrayList(starships))
            intent.putExtra(
                "STARSHIP_NAME",
                selectedStarship.name
            )
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
}





