package ru.task.hse_aboutme

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class sort : AppCompatActivity() {
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
        var sortAdapter = SortAdaper(items, this)
        var recyclerView = findViewById<RecyclerView>(R.id.sort_recycler)

        recyclerView.adapter = sortAdapter

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
}