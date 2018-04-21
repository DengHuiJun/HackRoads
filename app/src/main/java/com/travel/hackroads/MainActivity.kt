package com.travel.hackroads

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.travel.hackroads.net.HttpManager
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.ArrayAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val daysList = arrayOf("1天", "2天", "3天", "4天")
        val adapter1 = ArrayAdapter(this, R.layout.sp_item, daysList)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        daySp.adapter = adapter1

        val routeList = arrayOf("最短路线", "吃货路线", "最高性价比")
        val adapter2 = ArrayAdapter(this, R.layout.sp_item, routeList)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        strategySp.adapter = adapter2

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

    fun req() {
        val day = daySp.selectedItemPosition
        val strategy = strategySp.selectedItemPosition

        var b = reqBean()
        b.days = day + 1
        b.strategy = strategy + 1
        b.places = PlaceData.reqPlaces

        HttpManager.getInstance().requestRoads(b) {
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
