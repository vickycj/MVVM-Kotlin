package location.kotlin.apps.vicky.kotlin.data

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.recycler_view.view.*
import location.kotlin.apps.vicky.kotlin.R
import location.kotlin.apps.vicky.kotlin.model.PlacesModel

/**
 * Created by Vicky cj on 02-01-2018.
 */



class PlaceAdapter(private var places:List<PlacesModel>) : RecyclerView.Adapter<PlaceViewHolder>(){



    override fun onBindViewHolder(holder: PlaceViewHolder?, position: Int) {
                holder!!.bindItems(places.get(position),position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PlaceViewHolder {
            val v=LayoutInflater.from(parent!!.context).inflate(R.layout.recycler_view,parent,false)

        return PlaceViewHolder(v)
    }

    override fun getItemCount(): Int {
       return places.size
    }


    fun swapData(PlacesNew:List<PlacesModel>){
        if(places!=PlacesNew){
            places=PlacesNew
            notifyDataSetChanged()
        }

    }

}

