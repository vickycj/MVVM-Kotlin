package location.kotlin.apps.vicky.kotlin

import android.app.Application
import android.arch.lifecycle.*
import android.os.Bundle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import location.kotlin.apps.vicky.kotlin.Utils.InjectorUtils
import location.kotlin.apps.vicky.kotlin.model.PlaceViewModelFactory
import location.kotlin.apps.vicky.kotlin.model.PlacesModel
import location.kotlin.apps.vicky.kotlin.model.PlacesViewModel
import location.kotlin.apps.vicky.kotlin.repository.PlacesRepository
import location.kotlin.apps.vicky.kotlin.repository.Repository
import location.kotlin.apps.vicky.kotlin.room.RoomPlacesDatasource

/**
 * Created by vygne on 04-03-2018.
 */
class  Activity {


    lateinit var places:ArrayList<String>


    fun  onCreate(savedInstanceState: Bundle?){
        // Api Call

       // val factory: PlaceViewModelFactory = InjectorUtils.providePlaceRepository(this.applicationContext)

       // placesViewModel = ViewModelProviders.of(this,factory).get(PlacesViewModel::class.java)
       // placesViewModel?.let { lifecycle.addObserver(it) }
       /* placesViewModel?.fetchPlacesFromDB()!!.observe(this, Observer { places ->

            placesList= ArrayList()

            places?.forEach { place ->
                placesList.add(place)
            }

            adapter.swapData(placesList)


        })*/

    }
}



class PlacesViewModel(private val placesRepository: PlacesRepository) : ViewModel(), LifecycleObserver {

  /*  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unSubscribeViewModel() {
        for (disposable in placesRepository.allCompositeDisposable) {
            compositeDisposable.addAll(disposable)
        }
        compositeDisposable.clear()
    }*/
}


/*
class PlacesRepository (private val roomPlacesDatasource: RoomPlacesDatasource) : Repository {

    override fun getPlacesList(): LiveData<List<PlacesModel>> {
        val roomPlacesDao = roomPlacesDatasource.placeDao()
        val mutableLiveData = MutableLiveData<List<PlacesModel>>()

        val disposable = roomPlacesDao.getAllPlaces()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ placesList ->
                    mutableLiveData.value = transform(placesList)
                }, { t: Throwable? -> t!!.printStackTrace() })
        allCompositeDisposable.add(disposable)
        return mutableLiveData

    }

}*/
