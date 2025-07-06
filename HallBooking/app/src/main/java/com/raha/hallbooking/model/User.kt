package com.raha.hallbooking.model

data class User(
    val id: Int,
    val fullName: String ="",
    val email: String ="",
    val password: String = ""
)
