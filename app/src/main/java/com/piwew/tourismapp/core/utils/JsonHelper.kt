package com.piwew.tourismapp.core.utils

import android.content.Context
import com.piwew.tourismapp.R
import com.piwew.tourismapp.core.data.source.remote.response.TourismResponse
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(): String? {
        val jsonString: String
        try {
            jsonString = context.resources.openRawResource(R.raw.tourism)
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun loadData(): List<TourismResponse> {
        val list = ArrayList<TourismResponse>()
        val responseObject = JSONObject(parsingFileToString().toString())
        val listArray = responseObject.getJSONArray("places")

        for (i in 0 until listArray.length()) {
            val places = listArray.getJSONObject(i)

            val id = places.getString("id")
            val name = places.getString("name")
            val description = places.getString("description")
            val address = places.getString("address")
            val longitude = places.getDouble("longitude")
            val latitude = places.getDouble("latitude")
            val like = places.getInt("like")
            val image = places.getString("image")

            val placesResponse = TourismResponse(
                id = id,
                name = name,
                description = description,
                address = address,
                longitude = longitude,
                latitude = latitude,
                like = like,
                image = image
            )
            list.add(placesResponse)
        }
        return list
    }
}