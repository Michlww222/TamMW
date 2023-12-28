package com.android.wasik

import android.R
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.android.wasik.databinding.ActivityDetailBinding
import com.android.wasik.data.Starship

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var starships: List<Starship>
    private var currentIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        starships = intent.getParcelableArrayListExtra<Starship>("STARSHIP_LIST") ?: emptyList()
        val starshipName = intent.getStringExtra("STARSHIP_NAME")
        currentIndex = starships.indexOfFirst { it.name == starshipName }
        displayStarshipDetails(currentIndex)

        binding.btnReturnToList.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnNext.setOnClickListener {
            navigateToNextStarship()
        }
        binding.btnPrevious.setOnClickListener {
            navigateToPreviousStarship()
        }
        displayStarshipDetails(currentIndex)
        loadStarshipImage(starships[currentIndex].name)
    }

    private fun loadStarshipImage(starshipName: String) {
        var imageName = starshipName.toLowerCase().replace(" ", "_").replace("-", "_")
        var imageResId = resources.getIdentifier(imageName, "drawable", packageName)

        if (imageResId != 0) {
            binding.imageViewStarship.setImageResource(imageResId)
        } else {
            imageName = "not_found".replace(" ", "_").replace("-", "_")
            imageResId = resources.getIdentifier(imageName, "drawable", packageName)
        }
        binding.imageViewStarship.setImageResource(imageResId)
    }

    private fun displayStarshipDetails(index: Int) {
        if (index >= 0 && index < starships.size) {
            binding.textViewStarshipName.text = starships[index].name

            val starshipDetails = mutableListOf<String>()
            starshipDetails.add("Model: ${starships[index].model}")
            starshipDetails.add("Manufacturer: ${starships[index].manufacturer}")
            starshipDetails.add("Cost in Credits: ${starships[index].costInCredits}")
            //todo Add other details

            val adapter = ArrayAdapter(this, R.layout.simple_list_item_1, starshipDetails)

            binding.recyclerViewStarshipDetails.adapter = adapter
        } else {
            finish()
        }
    }

    private fun navigateToNextStarship() {
        currentIndex = (currentIndex + 1) % starships.size
        displayStarshipDetails(currentIndex)
        loadStarshipImage(starships[currentIndex].name)
    }

    private fun navigateToPreviousStarship() {
        currentIndex = (currentIndex - 1 + starships.size) % starships.size
        displayStarshipDetails(currentIndex)
        loadStarshipImage(starships[currentIndex].name)
    }
}






