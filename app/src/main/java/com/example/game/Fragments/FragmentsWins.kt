package com.example.game.Fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.game.R

class FragmentsWins : Fragment(R.layout.fragents_wins) {

    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)

        val listData:MutableList<ImageModels> = ArrayList()

        listData.add(ImageModels(R.drawable.firs))
        listData.add(ImageModels(R.drawable.secon))
        listData.add(ImageModels(R.drawable.thir))
        listData.add(ImageModels(R.drawable.fourt))
        listData.add(ImageModels(R.drawable.fift))
        listData.add(ImageModels(R.drawable.sixt))
        listData.add(ImageModels(R.drawable.sevent))
        listData.add(ImageModels(R.drawable.eight))

        val adapter = ImageAdapter(activity, listData)
        val layout = LinearLayoutManager(activity)
        layout.orientation = LinearLayoutManager.VERTICAL
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layout

    }
}