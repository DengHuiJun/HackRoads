package com.travel.hackroads

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.travel.hackroads.net.HttpManager
import kotlinx.android.synthetic.main.activity_add_place.*

class AddPlaceActivity : AppCompatActivity(), PlaceAdapter.ClickItemPlace {

    override fun addPlace(m: PlaceResult.PlacesBean) {

    }

    private lateinit var listAdapter :PlaceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_place)

        searchBar.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        listAdapter = PlaceAdapter(this, this)
        dataLv.adapter = listAdapter

        reqPlaces()

        done.setOnClickListener {
            doneResult()
        }
    }

    fun reqPlaces() {
        HttpManager.getInstance().requestPlaces {
            if (it.status == 200) {
                listAdapter.mData = it.places
                listAdapter.notifyDataSetChanged()

                PlaceData.resultPlaces = it.places
            } else {
                Toast.makeText(this, "加载错误～", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun doneResult() {
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
