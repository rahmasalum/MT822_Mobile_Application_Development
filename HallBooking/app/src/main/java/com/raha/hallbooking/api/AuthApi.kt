package com.raha.hallbooking.api

import com.raha.hallbooking.model.User
import com.raha.hallbooking.model.Hall
import com.raha.hallbooking.model.Booking
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {
    @GET("/api/users/email/{email}")
    suspend fun getUserByEmail(@Path("email") email: String): Response<User>
    @GET("api/bookings/user/{userId}")
    suspend fun getBookingsByUserId(@Path("userId") userId: Int): Response<List<Booking>>
    @GET("api/halls")
    suspend fun getAllHalls(): Response<List<Hall>>

    @POST("api/bookings")
    suspend fun createBooking(@Body booking: Booking): Response<Booking>


}