package com.example.kotlincallback

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlincallback.databinding.RowItemBinding

class RecyclerAdapter(
    val adapterOnClick: AdapterOnClick,
    val list: List<String>
) : RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(private val binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val item = binding.button
            item.text = list[position]
            item.setOnClickListener { adapterOnClick.onRowClick(itemName = item.text.toString()) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val root = RowItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        )
        return ItemViewHolder(root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

interface AdapterOnClick {
    fun onRowClick(itemName: String)
}