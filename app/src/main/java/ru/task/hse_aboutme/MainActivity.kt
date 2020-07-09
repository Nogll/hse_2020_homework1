package ru.task.hse_aboutme

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val myGithubUrl = "https://github.com/Nogll"
    private var data = BooleanArray(8)
    /*
    0 - < 1 month
    1 - < 1 year
    2-6 - pos - 1 year
    7 - all
    */
    var items = java.util.ArrayList<Lang>()
    private fun gen(){
        /*
        items = listOf(
            Lang("c++", 4),
            Lang("python", 4),
            Lang("java", 0),
            Lang("c#", 1),
            Lang("kotlin", -1)
        )
        */
        items.add(Lang("c++", 4))
        items.add(Lang("python", 4))
        items.add(Lang("java", 0))
        items.add(Lang("c#", 1))
        items.add(Lang("kotlin", -1))

        for(i in 0..4){
            if(data[7]) break

            if(!data[items[i].time + 1]) {
                items.removeAt(i)
            }
        }
    }
    fun onSortClick(view: View){
        val intent = Intent(this, sort::class.java)
        startActivityForResult(intent, 1)

    }
    fun onClickUrl(view : View){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(myGithubUrl))
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        for(i in 0..6){
            data[i] = true;
        }

        gen()

        val myAdapter = DataAdapter(items)
        val llm = LinearLayoutManager(this)
        val recyclerView: RecyclerView = findViewById<RecyclerView>(R.id.recycler)

        recyclerView.layoutManager = llm
        recyclerView.adapter = myAdapter

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1 && resultCode == Activity.RESULT_OK && data != null){
            this.data = data.getBooleanArrayExtra("sortStruct")

        }
    }
}