package location.kotlin.apps.vicky.kotlin.repository

import android.arch.lifecycle.LiveData
import io.reactivex.Flowable
import location.kotlin.apps.vicky.kotlin.model.PlacesModel

/**
 * Created by Vicky cj on 03-01-2018.
 */
interface Repository{
    fun getTotalPlaces(): Flowable<Int>

    fun addPlaces()

    fun getPlacesList(): LiveData<List<PlacesModel>>
}