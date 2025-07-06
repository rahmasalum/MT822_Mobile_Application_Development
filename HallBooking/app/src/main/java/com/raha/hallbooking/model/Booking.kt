package com.raha.hallbooking.model

data class Booking(
    val id: Int,
    val hall: Hall,
    val bookingDate: String,
    val timeSlot: String,
    val notes: String,
    val user: User
)
