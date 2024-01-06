package com.piwew.tourismapp.core.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.piwew.tourismapp.core.data.source.remote.network.ApiResponse
import com.piwew.tourismapp.core.data.source.remote.response.TourismResponse
import com.piwew.tourismapp.core.utils.JsonHelper
import org.json.JSONException

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    fun getAllTourism(): LiveData<ApiResponse<List<TourismResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<TourismResponse>>>()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            try {
                val dataArray = jsonHelper.loadData()
                if (dataArray.isNotEmpty()) {
                    resultData.value = ApiResponse.Success(dataArray)
                } else {
                    resultData.value = ApiResponse.Empty
                }
            } catch (e: JSONException) {
                resultData.value = ApiResponse.Error(e.toString())

            }
        }, 2000)

        return resultData
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }
}