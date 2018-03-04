package location.kotlin.apps.vicky.kotlin.room

/**
 * Created by Vicky cj on 03-01-2018.
 */
class RoomContract{
    companion object {
        const val DATABASE_PLACES = "places.db"

        const val TABLE_PLACES = "places"

        private const val SELECT_COUNT = "SELECT COUNT(*) FROM "
        private const val SELECT_FROM = "SELECT * FROM "

        const val SELECT_PLACES_COUNT = SELECT_COUNT + TABLE_PLACES
        const val SELECT_PLACES = SELECT_FROM + TABLE_PLACES
    }
}