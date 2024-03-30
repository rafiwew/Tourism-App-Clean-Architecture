package com.piwew.tourismapp.maps.di

import com.piwew.tourismapp.maps.MapsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mapsModule = module {
    viewModel { MapsViewModel(get()) }
}