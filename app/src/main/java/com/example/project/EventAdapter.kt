package com.example.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(val eventList : ArrayList<Event>) : RecyclerView.Adapter<EventAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.event_item,
        parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentitem = eventList[position]
        holder.direccion.text = currentitem.eventDirection
        holder.fecha.text = currentitem.eventDate
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val direccion : TextView = itemView.findViewById(R.id.Direcccion)
        val fecha : TextView = itemView.findViewById(R.id.Fecha)

    }
}