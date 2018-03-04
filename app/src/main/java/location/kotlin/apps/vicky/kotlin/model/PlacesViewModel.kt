package location.kotlin.apps.vicky.kotlin.model

import android.app.Application
import android.arch.lifecycle.*
import android.util.Log
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import location.kotlin.apps.vicky.kotlin.dependancy.KotlinApplication
import location.kotlin.apps.vicky.kotlin.repository.PlacesRepository
import location.kotlin.apps.vicky.kotlin.room.RoomPlacesDatasource
import javax.inject.Inject

/**
 * Created by Vicky cj on 03-01-2018.
 */
 class PlacesViewModel(private val placesRepository: PlacesRepository) : ViewModel(),LifecycleObserver{





    private val compositeDisposable = CompositeDisposable()
    private var livePlacesData: LiveData<List<PlacesModel>>? = null


    init {



       // initializeDagger()
    }


    private fun initializeDagger() = KotlinApplication.appComponent.inject(this)



    fun loadPlacesInDB() {
        val disposable = placesRepository.getTotalPlaces()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it==0) {
                        populate()
                    }
                }
        compositeDisposable.add(disposable)
    }


    private fun populate(){

        Completable.fromAction { placesRepository.addPlaces() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CompletableObserver {
                    override fun onSubscribe(@NonNull d: Disposable) {
                        compositeDisposable.add(d)
                    }

                    override fun onComplete() {


                    }

                    override fun onError(@NonNull e: Throwable) {
                        e.printStackTrace()

                    }
                })

    }


    fun fetchPlacesFromDB(): LiveData<List<PlacesModel>>? {
        if (livePlacesData == null) {
            livePlacesData = MutableLiveData<List<PlacesModel>>()
            livePlacesData = placesRepository.getPlacesList()
        }
        return livePlacesData
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
     fun unSubscribeViewModel() {
        for (disposable in placesRepository.allCompositeDisposable) {
            compositeDisposable.addAll(disposable)
        }
        compositeDisposable.clear()
    }

    override fun onCleared() {
        unSubscribeViewModel()
        super.onCleared()
    }


}