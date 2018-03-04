package location.kotlin.apps.vicky.kotlin

import android.os.Build.VERSION_CODES.LOLLIPOP
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import location.kotlin.apps.vicky.kotlin.model.PlacesViewModel
import location.kotlin.apps.vicky.kotlin.repository.PlacesRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Created by Vicky cj on 04-01-2018.
 */

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(LOLLIPOP), packageName = "location.kotlin.apps.vicky.kotlin")
class ViewModelTest{

    lateinit var placeRepository: PlacesRepository

    lateinit var viewModel:PlacesViewModel


    companion object {
        const val TOTALDATACOUNT=6

    }

    @Before
    fun setup(){
        placeRepository=PlacesRepository.getInstance(RuntimeEnvironment.application)
        viewModel= PlacesViewModel(placeRepository)
        viewModel.loadPlacesInDB()

    }


    @Test
    fun checkData(){
        placeRepository.getTotalPlaces() .subscribe({
            assertNotEquals(it,0)
        })
    }
    @Test
    fun checkCount(){
        placeRepository.getTotalPlaces() .subscribe({
            assertEquals(it,TOTALDATACOUNT)
        })
    }


}