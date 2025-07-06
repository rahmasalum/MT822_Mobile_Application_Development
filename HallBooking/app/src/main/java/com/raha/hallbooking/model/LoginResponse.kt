package com.raha.hallbooking.model
data class LoginResponse(
    val message: String,
    val token: String?,
    val userId: Int?
)

