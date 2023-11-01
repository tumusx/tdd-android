package com.example.tddandroid.data

import com.example.tddandroid.data.model.Cars
import com.google.android.material.animation.AnimationUtils

class DatabaseApplication {

    private val listItems = mutableListOf<Cars>()

    fun getAllItems() = listItems

    fun insertItems(items: Cars) = listItems.add(items)

}