package com.piwew.tourismapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.piwew.tourismapp.databinding.ActivityDetailTourismBinding

class DetailTourismActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTourismBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTourismBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        const val EXTRA_DATA = "DATA"
    }
}