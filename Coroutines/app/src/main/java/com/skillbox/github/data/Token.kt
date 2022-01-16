package com.skillbox.github.data

object Token {
    var accessToken = ""
    fun saveToken(token: String) {
        accessToken = token
    }
}