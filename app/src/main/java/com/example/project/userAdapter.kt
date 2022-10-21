package com.example.project

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView


class userAdapter(private val userList: ArrayList<User>) : RecyclerView.Adapter<userAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.donadores, parent,false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = userList[position]

        holder.name.text =currentitem.name
        holder.cant.text = currentitem.cant.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name : TextView = itemView.findViewById(R.id.nombre)
        val cant : TextView = itemView.findViewById(R.id.cant)
    }
}



