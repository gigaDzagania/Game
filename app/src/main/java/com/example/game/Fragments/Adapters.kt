package com.example.game.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.game.R

class ImageAdapter(var context: FragmentActivity?, var list: List<ImageModels>): RecyclerView.Adapter<ImageAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindData(data:ImageModels){
            val image: ImageView = itemView.findViewById(R.id.image_change)
            image.setImageResource(data.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.image_model,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.bindData(data)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}