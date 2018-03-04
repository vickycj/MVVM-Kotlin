package location.kotlin.apps.vicky.kotlin.data

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.recycler_view.view.*
import location.kotlin.apps.vicky.kotlin.model.PlacesModel

/**
 * Created by Vicky cj on 03-01-2018.
 */

class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


    fun bindItems(place:PlacesModel,position: Int){
        itemView.placeText.text=place.place
        itemView.subText.text=place.address

    }
}