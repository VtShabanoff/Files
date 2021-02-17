package com.example.generics

import kotlin.random.Random

sealed class Result<out T, R>{
    data class Success<T, R>(val success: T): Result<T, R>()
    data class Error<T, R>(val error: R): Result<T, R>()

    fun retResult(): Result<Int, String>{
        val randomRes = Random.nextBoolean()
        return if (randomRes) Error("error 404") else Success(1)
    }
}
