package location.kotlin.apps.vicky.kotlin.dependancy

import android.content.Context
import dagger.Module
import dagger.Provides
import location.kotlin.apps.vicky.kotlin.room.RoomPlacesDatasource
import javax.inject.Singleton

/**
 * Created by Vicky cj on 03-01-2018.
 */
@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomPlacesDataSource(context: Context)= RoomPlacesDatasource.buildPersistentPlaces(context)


}