package com.piwew.tourismapp.core.di

import android.content.Context
import androidx.room.Room
import com.piwew.tourismapp.core.data.source.local.room.TourismDao
import com.piwew.tourismapp.core.data.source.local.room.TourismDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): TourismDatabase = Room.databaseBuilder(
        context,
        TourismDatabase::class.java,
        "tourism.db"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideTourismDao(database: TourismDatabase): TourismDao = database.tourismDao()
}