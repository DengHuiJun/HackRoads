package com.travel.hackroads

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.travel.hackroads.net.HttpManager
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter
import com.jpardogo.android.googleprogressbar.library.FoldingCirclesDrawable
import android.preference.PreferenceManager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val daysList = arrayOf("1天", "2天", "3天", "4天")
        val adapter1 = ArrayAdapter(this, R.layout.sp_item, daysList)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        daySp.adapter = adapter1

        val routeList = arrayOf("最短路线", "吃货路线", "最优路线")
        val adapter2 = ArrayAdapter(this, R.layout.sp_item, routeList)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        strategySp.adapter = adapter2

        val d = FoldingCirclesDrawable.Builder(this)
                .colors(getProgressDrawableColors())
                .build()
        proBar.indeterminateDrawable = d

        setPlaceBtn.setOnClickListener {
            var i = Intent(this, AddPlaceActivity::class.java)
            startActivityForResult(i, 0x01)
        }

        findRoadsBtn.setOnClickListener {
            req()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == 0x01) {

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

    private fun req() {

        content.visibility = View.GONE
        proBar.visibility =View.VISIBLE

        val day = daySp.selectedItemPosition
        val strategy = strategySp.selectedItemPosition

        var b = reqBean()
        b.days = day + 1
        b.strategy = strategy + 1
        b.places = PlaceData.reqPlaces

        HttpManager.getInstance().requestRoads(applicationContext, b) {

            Handler().postDelayed({
                content.visibility = View.VISIBLE
                proBar.visibility = View.GONE
            }, 1000L)

            if(it.status != 200) {
                Toast.makeText(this, "查询失败~", Toast.LENGTH_SHORT).show()
                return@requestRoads
            }

            PlaceData.resultPositions = it.param.positions

            if (PlaceData.resultPositions != null && PlaceData.resultPositions.size > 0) {
                go()
            } else {
                Toast.makeText(this, "查询失败～", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun go() {
        var i = Intent(this, RoadsActivity::class.java)
        startActivity(i)
    }
}
