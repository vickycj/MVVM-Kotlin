package location.kotlin.apps.vicky.kotlin.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by Vicky cj on 03-01-2018.
 */

@Database(
        entities = [PlacesEntity::class],
        version = 1)
abstract class RoomPlacesDatasource:RoomDatabase(){

    abstract fun placeDao(): RoomPlaceDao


    companion object {

        fun buildPersistentPlaces(context: Context): RoomPlacesDatasource = Room.databaseBuilder(
                context.applicationContext,
                RoomPlacesDatasource::class.java,
                RoomContract.DATABASE_PLACES
        ).build()


        fun getAllPlaces(): List<PlacesEntity> {
            val mutablePlacesList = mutableListOf<PlacesEntity>()
            mutablePlacesList.add(createEntity("American Swedish Institute",
                    "2600 Park Ave, Minneapolis, MN 55407, USA",
                    44.9548083,-93.2682871))

            mutablePlacesList.add(createEntity("Frederick R. Weisman Art Museum",
                    "333 E River Pkwy, Minneapolis, MN 55455, USA",
                    44.9731181,-93.2391883))

            mutablePlacesList.add(createEntity("Minneapolis Institute of Art",
                    "2400 3rd Ave S, Minneapolis, MN 55404, USA",
                    44.958539,-93.2763935))

            mutablePlacesList.add(createEntity("Basilica of Saint Mary",
                    "88 17th St N, Minneapolis, MN 55403, USA",
                    44.9730524,-93.2884244))

            mutablePlacesList.add(createEntity("Guthrie Theater",
                    "818 S 2nd St, Minneapolis, MN 55415, USA",
                    44.9781059,-93.2581319))

            mutablePlacesList.add(createEntity("Ascension Place of Haven ",
                    "1803 Bryant Ave N, Minneapolis, MN 55411, USA",
                    44.997724,-93.2935987))

            return mutablePlacesList
        }

        private fun createEntity(name: String, address: String, latitude:Double,longitude:Double) =
                PlacesEntity(0, name, address,latitude,longitude)
    }


}