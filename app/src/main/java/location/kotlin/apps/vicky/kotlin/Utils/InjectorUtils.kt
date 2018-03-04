package location.kotlin.apps.vicky.kotlin.Utils

import android.content.Context
import location.kotlin.apps.vicky.kotlin.model.PlaceViewModelFactory
import location.kotlin.apps.vicky.kotlin.repository.PlacesRepository

/**
 * Created by Vicky cj on 04-01-2018.
 */
class InjectorUtils{
    companion object {
        fun providePlaceRepository(context: Context): PlaceViewModelFactory {
            return PlaceViewModelFactory(PlacesRepository.getInstance(context))
        }
    }


}