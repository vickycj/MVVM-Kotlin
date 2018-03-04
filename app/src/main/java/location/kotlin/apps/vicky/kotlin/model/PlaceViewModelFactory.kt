package location.kotlin.apps.vicky.kotlin.model

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import location.kotlin.apps.vicky.kotlin.repository.PlacesRepository

/**
 * Created by Vicky cj on 04-01-2018.
 */
class PlaceViewModelFactory (private val placesRepository: PlacesRepository): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlacesViewModel(placesRepository) as T
    }

}