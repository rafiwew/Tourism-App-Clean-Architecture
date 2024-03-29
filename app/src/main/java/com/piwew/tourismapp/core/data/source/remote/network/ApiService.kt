package com.piwew.tourismapp.core.data.source.remote.network

import com.piwew.tourismapp.core.data.source.remote.response.ListTourismResponse
import io.reactivex.Flowable
import retrofit2.*
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    fun getList(): Flowable<ListTourismResponse>
}