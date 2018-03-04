package location.kotlin.apps.vicky.kotlin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.SnapHelper
import android.widget.LinearLayout
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.maps.model.CameraPosition
import location.kotlin.apps.vicky.kotlin.data.PlaceAdapter


import android.widget.AbsListView
import android.support.v7.widget.RecyclerView
import com.google.android.gms.maps.*
import location.kotlin.apps.vicky.kotlin.Utils.Constants
import location.kotlin.apps.vicky.kotlin.model.PlaceViewModelFactory
import location.kotlin.apps.vicky.kotlin.model.PlacesModel
import location.kotlin.apps.vicky.kotlin.model.PlacesViewModel
import location.kotlin.apps.vicky.kotlin.Utils.InjectorUtils




class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mGoogleMap:GoogleMap?=null
    private var placesViewModel: PlacesViewModel? = null
    var placesList=ArrayList<PlacesModel>()
    private lateinit var adapter:PlaceAdapter
    private var position:Int=0


    override fun onMapReady(googleMap: GoogleMap?) {
        mGoogleMap= googleMap
        loadMarkers()
        loadNext(position)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         adapter=PlaceAdapter(placesList)


        val layoutMan=LinearLayoutManager(this,orientationCheck(), false)
        listOfPlaces.layoutManager=layoutMan
        listOfPlaces.setHasFixedSize(true)
        listOfPlaces.adapter=adapter
        var snapHelper: SnapHelper=LinearSnapHelper()
        snapHelper.attachToRecyclerView(listOfPlaces)


        if(savedInstanceState!=null){
            position=savedInstanceState.getInt(Constants.ADAPTERPOSITION)
        }

        listOfPlaces.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)
                    position=layoutMan.getPosition(snapHelper.findSnapView(layoutMan))
                   loadNext(position)
            }
        })


        initViewModel()
        fetchData()



        MapsInitializer.initialize(this)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initViewModel() {

        val factory:PlaceViewModelFactory = InjectorUtils.providePlaceRepository(this.applicationContext)

        placesViewModel = ViewModelProviders.of(this,factory).get(PlacesViewModel::class.java)
        placesViewModel?.let { lifecycle.addObserver(it) }
        placesViewModel?.loadPlacesInDB()
    }

    private fun fetchData(){
        placesViewModel?.fetchPlacesFromDB()!!.observe(this, Observer { places ->

            placesList= ArrayList()

            places?.forEach { place ->
                placesList.add(place)
            }

            adapter.swapData(placesList)


        })
    }

    private fun loadNext(pos: Int) = plotMap(placesList[pos])


    private fun orientationCheck():Int
      = when( this.resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT ->  LinearLayout.HORIZONTAL
            else ->  LinearLayout.VERTICAL
        }


    fun loadMarkers(){
        mGoogleMap?.clear()
        for (place in placesList){
            val location = LatLng(place.latitude, place.longitude)

            mGoogleMap?.addMarker(MarkerOptions().position(location).title(place.place))
        }
    }


    private fun plotMap(placesModel: PlacesModel){


        val location = LatLng(placesModel.latitude, placesModel.longitude)
         val cameraPosition = CameraPosition.Builder()
                .target(location)
                .zoom(25f)
                .build()


        mGoogleMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    override fun onSaveInstanceState(outState: Bundle?) {

        outState?.putInt(Constants.ADAPTERPOSITION,position)
        super.onSaveInstanceState(outState)
    }


}
