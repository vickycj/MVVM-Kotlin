package location.kotlin.apps.vicky.kotlin.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Vicky cj on 03-01-2018.
 */
@Entity(tableName = RoomContract.TABLE_PLACES)
data class PlacesEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var place: String,
    var address: String,
    var latitude: Double,
    var longitude: Double
)