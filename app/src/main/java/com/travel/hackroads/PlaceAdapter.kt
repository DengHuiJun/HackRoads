package com.travel.hackroads

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.gc.materialdesign.views.CheckBox
import com.travel.hackroads.PlaceResult.PlacesBean

/**
 * Created by Allen.D on 18/4/21.
 */
class PlaceAdapter(context : Context, listener : ClickItemPlace) : BaseAdapter()  {

    var mData: List<PlacesBean> = ArrayList()

    var orderListener : ClickItemPlace = listener

    private val layoutInflater = LayoutInflater.from(context)

    override fun getCount() = mData.size

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v = convertView ?: layoutInflater.inflate(R.layout.place_item, parent, false)

        val data : PlacesBean = mData[position]

        val name = v.findViewById<TextView>(R.id.placeNameTv)
        val ck = v.findViewById<CheckBox>(R.id.checkBox)

        name.text = data.name
        ck.setOncheckListener({ view, check ->
            orderListener.checkPlace(data, check)
        })

        return v
    }

    override fun getItem(position: Int): PlacesBean {
        return mData[position]
    }

    override fun getItemId(position: Int): Long {
        return mData[position].id.toLong()
    }

    interface ClickItemPlace {
        fun checkPlace(m:PlacesBean, b:Boolean)
    }
}