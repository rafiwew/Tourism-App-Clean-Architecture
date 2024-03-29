package com.piwew.tourismapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.piwew.tourismapp.R
import com.piwew.tourismapp.core.domain.model.Tourism
import com.piwew.tourismapp.core.ui.ViewModelFactory
import com.piwew.tourismapp.core.utils.loadImage
import com.piwew.tourismapp.databinding.ActivityDetailTourismBinding

class DetailTourismActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTourismBinding
    private val viewModel by viewModels<DetailTourismViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTourismBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        showDetailTourism(intent.getParcelableExtra(EXTRA_DATA))
    }

    private fun showDetailTourism(detailTourism: Tourism?) {
        detailTourism?.let {
            supportActionBar?.title = detailTourism.name
            binding.content.tvDetailDescription.text = detailTourism.description
            binding.ivDetailImage.loadImage(detailTourism.image)

            var statusFavorite = detailTourism.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                viewModel.setFavoriteTourism(detailTourism, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        binding.fab.setImageDrawable(
            ContextCompat.getDrawable(
                this@DetailTourismActivity,
                if (statusFavorite) R.drawable.ic_favorite_white else R.drawable.ic_not_favorite_white
            )
        )
    }

    companion object {
        const val EXTRA_DATA = "DATA"
    }
}