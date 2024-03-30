package com.piwew.tourismapp.maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.piwew.core.data.source.Resource
import com.piwew.tourismapp.maps.databinding.ActivityMapsBinding
import com.piwew.tourismapp.maps.di.mapsModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MapsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapsBinding
    private val mapsViewModel: MapsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(mapsModule)

        supportActionBar?.title = getString(R.string.tourism_map)

        getTourismData()
    }

    private fun getTourismData() {
        mapsViewModel.tourism.observe(this) { tourism ->
            binding.apply {
                when (tourism) {
                    is Resource.Loading -> {
                        progressBar.visibility = View.VISIBLE
                        tvError.visibility = View.GONE
                    }

                    is Resource.Success -> {
                        progressBar.visibility = View.GONE
                        tvMaps.text = getString(R.string.map_of, tourism.data?.get(0)?.name)
                    }

                    is Resource.Error -> {
                        progressBar.visibility = View.GONE
                        tvError.visibility = View.VISIBLE
                        tvError.text = tourism.message
                    }
                }
            }
        }
    }
}