package location.kotlin.apps.vicky.kotlin.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import location.kotlin.apps.vicky.kotlin.model.PlacesModel
import location.kotlin.apps.vicky.kotlin.room.PlacesEntity
import location.kotlin.apps.vicky.kotlin.room.RoomPlacesDatasource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Vicky cj on 03-01-2018.
 */

class PlacesRepository (private val roomPlacesDatasource: RoomPlacesDatasource) : Repository{

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

    companion object {

        @Volatile private var INSTANCE: PlacesRepository? = null

        fun getInstance(context: Context): PlacesRepository =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: loadRepository(context).also { INSTANCE = it }
                }

        private fun loadRepository(context: Context):PlacesRepository{
            return PlacesRepository(RoomPlacesDatasource.buildPersistentPlaces(context))
        }

    }



        val allCompositeDisposable: MutableList<Disposable> = arrayListOf()




    override fun getTotalPlaces(): Flowable<Int> {
       return roomPlacesDatasource.placeDao().getPlacesTotal()
    }

    override fun addPlaces() {
        val placesEntityList = RoomPlacesDatasource.getAllPlaces()
        roomPlacesDatasource.placeDao().insertAll(placesEntityList)
    }



    fun transform(places:List<PlacesEntity>):List<PlacesModel>{
            var placesList=ArrayList<PlacesModel>()

        places.forEach { placesList.add(PlacesModel(it.place,it.address,it.latitude,it.longitude)) }

        return placesList
    }

}