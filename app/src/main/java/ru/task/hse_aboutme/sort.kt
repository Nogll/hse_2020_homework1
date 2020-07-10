package ru.task.hse_aboutme

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class sort : AppCompatActivity() {

    companion object{
        val DATA = "dataArray"
    }
    private var bundleData: BooleanArray? = null
    private lateinit var sortAdapter: SortAdaper

    private var data = BooleanArray(8)
    /*
    0 - < 1 month
    1 - < 1 year
    2-6 - pos - 1 year
    7 - all
     */

    var items = listOf<String>(
        "< 1 month",
        "< 1 year",
        "1 year",
        "2 year",
        "3 year",
        "4 year",
        "> 4 year",
        "all"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sort)
        for(i in 0..6){
            data[i] = false;
        }

        if(savedInstanceState != null)bundleData = savedInstanceState?.getBooleanArray(DATA)!!
        if(bundleData != null){
            data = bundleData?:data
        }

        sortAdapter = SortAdaper(items, this)
        var recyclerView = findViewById<RecyclerView>(R.id.sort_recycler)

        recyclerView.adapter = sortAdapter
    }

    override fun onStart() {
        super.onStart()
        if(bundleData != null) {
            val dbg = findViewById<TextView>(R.id.dbg)

            dbg.text = "updated"
            sortAdapter.setData(data)
        }
    }

    public fun onClick(s: Int, state: Boolean){
        //var checkBox: CheckBox = view
        data[s] = state
    }
    fun onSetClick(view: View){
        var intent = Intent()
        intent.putExtra("sortStruct", data)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBooleanArray(DATA, data)
        super.onSaveInstanceState(outState)
    }
}