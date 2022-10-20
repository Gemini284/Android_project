package com.example.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class BuscarEventoDonador : AppCompatActivity() {

    lateinit var dbRef : DatabaseReference
    lateinit var eventRecyclerView : RecyclerView
    lateinit var eventArrayList : ArrayList<Event>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_evento_donador)

        eventRecyclerView = findViewById(R.id.EventList)
        eventRecyclerView.layoutManager = LinearLayoutManager(this)
        eventRecyclerView.setHasFixedSize(true)

        eventArrayList = arrayListOf<Event>()
        getEventData()

    }

    private fun getEventData() {

        dbRef = FirebaseDatabase.getInstance().getReference("Eventos")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(eventsSnapshot in snapshot.children){
                        val event = eventsSnapshot.getValue(Event::class.java)
                        eventArrayList.add(event!!)


                    }
                    eventRecyclerView.adapter = EventAdapter(eventArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
    fun Regresar(v: View?) {
        finish();
    }

}