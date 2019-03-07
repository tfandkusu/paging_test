package com.example.pagingtest

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

/**
 *  PagedListに対応したアダプター
 */
class StoreListAdapter(diffCallback: DiffUtil.ItemCallback<Store>) :
    PagedListAdapter<Store, StoreListViewHolder>(diffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreListViewHolder {
        // 1要素分のViewを作る
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.store, parent, false)
        return StoreListViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreListViewHolder, position: Int) {
        // データに基づきViewを変更する
        getItem(position)?.let {
            holder.chainView.text = it.chain
            holder.placeView.text = it.place
        }
    }
}