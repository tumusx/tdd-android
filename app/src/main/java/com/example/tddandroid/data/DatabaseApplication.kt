package com.example.tddandroid.data

import com.example.tddandroid.data.model.Cars
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DatabaseApplication {

    private val listItems = mutableListOf<Cars>()

    fun getAllItems() = listItems

    fun insertItems(items: Cars) = listItems.add(items)

}