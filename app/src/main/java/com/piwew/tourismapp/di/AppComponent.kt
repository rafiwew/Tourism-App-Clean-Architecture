package com.piwew.tourismapp.di

import com.piwew.tourismapp.core.di.CoreComponent
import com.piwew.tourismapp.detail.DetailTourismActivity
import com.piwew.tourismapp.favorite.FavoriteFragment
import com.piwew.tourismapp.home.HomeFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: HomeFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(activity: DetailTourismActivity)
}