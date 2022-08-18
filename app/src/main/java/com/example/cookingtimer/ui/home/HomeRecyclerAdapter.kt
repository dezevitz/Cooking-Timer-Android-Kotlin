package com.example.cookingtimer.ui.home

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingtimer.R
import android.widget.Button

class HomeRecyclerAdapter(private val timerList: List<String>) : RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_timer_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.time.text = timerList[position]

        //initialize the listener
        holder.onClick(holder.item)
    }

    override fun getItemCount(): Int {
        return timerList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), OnClickListener {
        val item = itemView
        val time: TextView = item.findViewById(R.id.time_textView)

        override fun onClick(itemView: View) {
            val startButton: Button = itemView.findViewById(R.id.start_button)
            startButton.setOnClickListener{
                time.text = "abcdefg"
            }
        }

    }


}

interface OnClickListener {
    fun onClick(itemView: View)
}