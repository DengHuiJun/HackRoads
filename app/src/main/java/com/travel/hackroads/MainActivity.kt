package com.travel.hackroads

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.travel.hackroads.net.HttpManager
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        test.setOnClickListener {
            req()
        }

        findRoadsBtn.setOnClickListener {
            var i = Intent(this, RoadsActivity::class.java)
            startActivity(i)
        }

    }

    fun req() {
        var c = Consumer<Result> {

            val re = it

            Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
        }

        var b = Bean()
        b.days = 1
        b.strategy = 1

        val list = ArrayList<Bean.PositionsBean>()
        val pos = Bean.PositionsBean()
        pos.lng = 23.397972
        pos.lat = 116.397972
        pos.place = "北京"
        list.add(pos)

        b.positions = list

        HttpManager.getInstance().requestRoads(c, b)
    }
}
