package com.skillbox.messageapp.domain.models

data class Contact(
    val id: Long,
    val name: String
){
    val userId: Long = 0L
}
