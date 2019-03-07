package com.example.pagingtest

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StoreListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     * チェーン名
     */
    val chainView : TextView

    /**
     * 店舗名
     */
    val placeView : TextView


    init {
        chainView = itemView.findViewById<TextView>(R.id.chain)
        placeView = itemView.findViewById<TextView>(R.id.place)
    }
}