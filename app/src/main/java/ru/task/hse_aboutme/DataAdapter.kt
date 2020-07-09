package ru.task.hse_aboutme

import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(items : List<Lang>) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private var lang : TextView = view.findViewById(R.id.lang)
        private var skillTime : TextView = view.findViewById(R.id.skill_time)
        fun onBind(l : Lang){
            lang.text = l.name

            skillTime.text = when(l.time){
                -1 -> "< 1 month"
                0 -> "< 1 year"
                1 -> "1 year"
                else -> l.time.toString() + " years"
            }
        }

    }
    private var data = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.skills_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.onBind(data[position])
    }
}