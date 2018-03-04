package location.kotlin.apps.vicky.kotlin.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * Created by Vicky cj on 03-01-2018.
 */
@Dao
interface RoomPlaceDao{


    @Query(RoomContract.SELECT_PLACES_COUNT)
    fun getPlacesTotal(): Flowable<Int>

    @Insert
    fun insertAll(places: List<PlacesEntity>)

    @Query(RoomContract.SELECT_PLACES)
    fun getAllPlaces(): Flowable<List<PlacesEntity>>


}