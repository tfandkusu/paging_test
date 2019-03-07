package com.example.pagingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 参考文献
 * https://developer.android.com/reference/android/arch/paging/PagedListAdapter.html
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1ページ50個、10個先をあらかじめ読んでおく設定
        val config = PagedList.Config.Builder().setPageSize(50)
            .setPrefetchDistance(10).build()
        // 店舗情報取得用のDataStoreのFactory
        val factory = StoreDataSourceFactory()
        // このへんはテンプレ
        val livePagedList = LivePagedListBuilder(factory, config).build()
        val itemCallback = StoreItemCallback()

        //RecyclerViewを作成する
        val layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        // Adapterを作成して設定する
        val adapter = StoreListAdapter(itemCallback)
        recyclerView.adapter = adapter

        livePagedList.observe(this, Observer {
            Log.d("Takada","observe")
            adapter.submitList(it)
        })
    }

}
