package com.gb.poplib.githubclient

class CountersModel {

    val counters = mutableListOf(0, 0, 0)

    fun getItem(index: Int): Int {
        return counters[index]
    }

    fun nextItem(index: Int): Int {
        return ++counters[index]
    }

    fun setItem(index: Int, value: Int) {
        counters[index] = value
    }
}