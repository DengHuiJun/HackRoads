package com.travel.hackroads

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.View
import android.widget.Toast
import com.jpardogo.android.googleprogressbar.library.ChromeFloatingCirclesDrawable
import com.travel.hackroads.net.HttpManager
import kotlinx.android.synthetic.main.activity_add_place.*

class AddPlaceActivity : AppCompatActivity(), PlaceAdapter.ClickItemPlace {
    override fun checkPlace(m: PlaceResult.PlacesBean, b: Boolean) {
        if (b) {
            PlaceData.reqPlaces.add(m.id)
        } else{
            PlaceData.reqPlaces.remove(m.id)
        }
    }

    private lateinit var listAdapter :PlaceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_place)

        searchBar.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        val d = ChromeFloatingCirclesDrawable.Builder(this)
                .colors(getProgressDrawableColors())
                .build()
        proBar.indeterminateDrawable = d

        listAdapter = PlaceAdapter(this, this)
        dataLv.adapter = listAdapter

        reqPlaces()

        done.setOnClickListener {
            doneResult()
        }
    }

    private fun getProgressDrawableColors(): IntArray {
        val colors = IntArray(4)
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        colors[0] = prefs.getInt(getString(R.string.firstcolor_pref_key), resources.getColor(R.color.red))
        colors[1] = prefs.getInt(getString(R.string.secondcolor_pref_key), resources.getColor(R.color.blue))
        colors[2] = prefs.getInt(getString(R.string.thirdcolor_pref_key), resources.getColor(R.color.yellow))
        colors[3] = prefs.getInt(getString(R.string.fourthcolor_pref_key), resources.getColor(R.color.green))
        return colors
    }

    fun reqPlaces() {
        dataLv.visibility = View.GONE
        proBar.visibility = View.VISIBLE

        HttpManager.getInstance().requestPlaces(applicationContext) {

            Handler().postDelayed({
                dataLv.visibility = View.VISIBLE
                proBar.visibility = View.GONE
            }, 100)

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
