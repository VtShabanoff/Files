package com.example.generics

class Queue<T> {
    var items: MutableList<T> = mutableListOf()
    fun enqueue(item: T){
        items.add(item)
    }

    fun dequeue(): T?{
        return if (items.isNotEmpty()) items[0] else null

    }
}