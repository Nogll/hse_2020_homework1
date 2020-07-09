package ru.task.hse_aboutme

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class sort : AppCompatActivity() {
    private var data = BooleanArray(8)
    /*
    0 - < 1 month
    1 - < 1 year
    2-6 - pos - 1 year
    7 - all
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sort)
        for(i in 0..6){
            data[i] = true;
        }
    }

    fun onSetClick(view: View){
        var intent = Intent()
        intent.putExtra("sortStruct", data)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}