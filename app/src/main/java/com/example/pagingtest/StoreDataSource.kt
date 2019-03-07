package com.example.pagingtest

import android.os.Looper
import android.util.Log
import androidx.paging.ItemKeyedDataSource

/**
 * PagedList用に店舗情報を取得する
 */
class StoreDataSource : ItemKeyedDataSource<Int,Store>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Store>) {
        //最初のデータを読み込み
        val size = 50
        Log.d("Takada","loadInitial $size 個 " + getThreadName())
        val list = mutableListOf<Store>()
        for(i in 0..(size - 1))
            list.add(createStore(i))
        callback.onResult(list)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Store>) {
        //次以降のデータを読み込み
        val start = params.key + 1
        val size = params.requestedLoadSize
        val end = start + size
        Log.d("Takada", "loadAfter $start から $end " + getThreadName())
        val list = mutableListOf<Store>()
        for(i in start..(start + size - 1)){
            list.add(createStore(i))
        }
        // 読み込みに時間がかかる再現
        Thread.sleep(1000)
        callback.onResult(list)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Store>) {

    }

    override fun getKey(item: Store): Int {
        // ユニークなキーを返す
        return item.id
    }

    /**
     * 架空の店舗を作成する
     * @param index 何番目か
     */
    private fun createStore(index : Int) : Store{
        return Store(index, "RNIマート", "赤坂 $index 号店")
    }

    /**
     * 動いているスレッドがメインスレッドかワーカースレッドかを返却する
     */
    private fun getThreadName() : String{
        if(Thread.currentThread().id == Looper.getMainLooper().thread.id){
            return "Main thread"
        }else{
            return "Worker thread"
        }
    }
}