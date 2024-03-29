package com.piwew.tourismapp.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.piwew.tourismapp.core.data.source.local.entity.TourismEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface TourismDao {

    @Query("SELECT * FROM tourism")
    fun getAllTourism(): Flowable<List<TourismEntity>>

    @Query("SELECT * FROM tourism WHERE isFavorite = 1")
    fun getFavoriteTourism(): Flowable<List<TourismEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTourism(tourism: List<TourismEntity>): Completable

    @Update
    fun updateFavoriteTourism(tourismEntity: TourismEntity)
}