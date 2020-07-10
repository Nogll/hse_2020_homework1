package ru.task.hse_aboutme

import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView

class SortAdaper(items : List<String>, private val sortObject: sort) : RecyclerView.Adapter<SortAdaper.ViewHolder>() {

    private var boxes: ArrayList<CheckBox> = ArrayList<CheckBox>()
    private var toSetData = false
    private lateinit var dataBool: BooleanArray

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var checkBoxView: CheckBox = view.findViewById(R.id.checkBox)
        fun onBind(str: String, pos: Int){
            val adaper = View.OnClickListener{
                sortObject.onClick(adapterPosition, checkBoxView.isChecked)
                if(!checkBoxView.isChecked){
                    sortObject.onClick(7, false)
                    boxes[7].isChecked = false
                }
            }
            val adaper2 = View.OnClickListener{

                for(i in 0..6){
                    boxes[i].isChecked = boxes[7].isChecked
                    sortObject.onClick(i, boxes[7].isChecked)
                }

                sortObject.onClick(adapterPosition, checkBoxView.isChecked)
            }
            boxes.add(checkBoxView)
            if(toSetData && boxes.size == 8){
                for(i in 0..7){
                    boxes[i].isChecked = dataBool[i]
                }
            }

            checkBoxView.text = str
            if(pos != 7)checkBoxView.setOnClickListener(adaper)
            else checkBoxView.setOnClickListener(adaper2)
        }

    }
    var data = items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SortAdaper.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.sort_items, parent, false))
    }
    override fun getItemCount(): Int {

        return data.size
    }

    override fun onBindViewHolder(holder: SortAdaper.ViewHolder, position: Int) {
        holder.onBind(data[position], position)
    }

    public fun setData(dataBool: BooleanArray){
        if(boxes.size == 0){
            toSetData = true
            this.dataBool = dataBool
            return
        }

        for(i in 0..7){
            boxes[i].isChecked = dataBool[i]
        }
    }
}