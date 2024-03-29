package com.piwew.tourismapp

import android.app.Application
import com.piwew.tourismapp.core.di.CoreComponent
import com.piwew.tourismapp.core.di.DaggerCoreComponent
import com.piwew.tourismapp.di.AppComponent
import com.piwew.tourismapp.di.DaggerAppComponent

open class MyApplication : Application() {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}