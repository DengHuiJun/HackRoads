package com.travel.hackroads

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(),PlaceAdapter.ClickItemPlace {
    override fun checkPlace(m: PlaceResult.PlacesBean, b: Boolean) {

    }

    var keyWords : String = ""

    val handler = Handler()

    val searchRunnable = Runnable {
        loadData()
    }

    private lateinit var listAdapter :PlaceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        cancel_tv.setOnClickListener {
            finish()
        }

        search_keyword_et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                keyWords = p0.toString()
                handler.post(searchRunnable)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                handler.removeCallbacks(searchRunnable)
            }
        })

        listAdapter = PlaceAdapter(this, this)

        searchLv.adapter = listAdapter

    }

    private fun loadData() {
//        DBManager.db.menuDao().searchMenuByKeyWord("%" + keyWords +"%")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    if (it != null && !it.isEmpty()) {
//                        listAdapter.mData = it
//                        listAdapter.notifyDataSetChanged()
//                    }
//                }
    }
}
