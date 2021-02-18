package com.example.generics

import java.util.function.BiPredicate


class Queue<T> {
    var items: MutableList<T> = mutableListOf()
    fun enqueue(item: T){
        items.add(item)
    }

    fun dequeue(): T?{
        return if (items.isNotEmpty()) items[0] else null

    }

    fun filterQueue(predicate: (T) -> Boolean ): Queue<T> {
        val newQueue = Queue<T>()
            for (item in this.items){
                if (predicate(item)){
                newQueue.items.add(item)
            }
        }
        return newQueue
    }
}