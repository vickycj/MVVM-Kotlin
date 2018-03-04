package location.kotlin.apps.vicky.kotlin.dependancy

import android.app.Application
import dagger.Component
import location.kotlin.apps.vicky.kotlin.model.PlacesViewModel
import javax.inject.Singleton

/**
 * Created by Vicky cj on 03-01-2018.
 */

@Component(modules = arrayOf(AppModule::class, RoomModule::class))
@Singleton
interface AppComponent{
    fun inject (placesViewModel: PlacesViewModel)
}

