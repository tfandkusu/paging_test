package com.example.pagingtest

import androidx.paging.DataSource

class StoreDataSourceFactory : DataSource.Factory<Int,Store>() {
    override fun create(): DataSource<Int, Store> {
        return StoreDataSource()
    }
}