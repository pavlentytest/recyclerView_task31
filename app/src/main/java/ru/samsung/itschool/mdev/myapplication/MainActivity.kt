package ru.samsung.itschool.mdev.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var staffs: List<Staff>
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fileResult = application.assets.open("staff.json").bufferedReader().use { it.readText() }
        Log.d("RRR",fileResult)
        val gson = GsonBuilder().create()
        staffs = gson.fromJson(fileResult,Array<Staff>::class.java).toList()
        Log.d("RRR",staffs.get(0).name)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(staffs)
        recyclerView.adapter = adapter
        btn1 = findViewById(R.id.byname)
        btn2 = findViewById(R.id.bysex)
        btn3 = findViewById(R.id.bynumber)
        sortBy()
    }

    fun sortBy() {
        btn1.setOnClickListener {
            recyclerView.adapter = MyAdapter(staffs.sortedBy { it.name })
        }
        btn2.setOnClickListener {
            recyclerView.adapter = MyAdapter(staffs.sortedBy { it.sex })
        }
        btn3.setOnClickListener {
            recyclerView.adapter = MyAdapter(staffs.sortedBy { it.phoneNumber })
        }
    }
}
data class Staff(val name: String, val sex: Char, val phoneNumber: String)