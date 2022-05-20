package com.example.kotlincallback

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlincallback.databinding.ActivityMainBinding
import kotlin.random.Random

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), AdapterOnClick {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter2: RecyclerAdapter
    private var randomNumber:Int = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter2 = RecyclerAdapter(this, getList(randomNumber))
        binding.recyclerView.apply {
            adapter = adapter2
            layoutManager = LinearLayoutManager(context)
        }
        binding.swipeView.setOnRefreshListener {
            binding.swipeView.isRefreshing = false
            rearrangeItems()
        }
    }

    private fun rearrangeItems() {
        randomNumber = Random.nextInt(0, 100)
        adapter2 = RecyclerAdapter(this,getList(randomNumber))
        binding.recyclerView.adapter =adapter2
    }

    private fun getList(num: Int): List<String> {
        val list = mutableListOf<String>()
        for (i in 0..num) {
            list.add(i, "$i")
        }
        return list
    }

    override fun onRowClick(itemName: String) {
        Log.d(TAG, "onRowClick: $itemName")
    }
}