package com.raha.hallbooking.model

data class Hall(
    val id: Long,
    val name: String,
val location: String,
val capacity: Int,
val available: Boolean
)
