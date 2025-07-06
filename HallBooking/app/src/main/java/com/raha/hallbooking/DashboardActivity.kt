package com.raha.hallbooking

import android.os.Bundle
import android.content.Intent
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raha.hallbooking.adapter.BookingAdapter
import com.raha.hallbooking.api.RetrofitClient
import com.raha.hallbooking.model.Booking
import kotlinx.coroutines.*

class DashboardActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userNameTitle: TextView
    private val coroutineScope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val userId = intent.getIntExtra("userId", -1)
        findViewById<Button>(R.id.book).setOnClickListener {
            val intent = Intent(this, BookingActivity::class.java)
            intent.putExtra("userId", userId)

            startActivity(intent)
        }

        val userName = intent.getStringExtra("userName") ?: "User"

        userNameTitle = findViewById(R.id.userNameTitle)
        recyclerView = findViewById(R.id.bookingRecyclerView)

        userNameTitle.text = "Welcome, $userName"

        recyclerView.layoutManager = LinearLayoutManager(this)

        if (userId != -1) {
            coroutineScope.launch {
                try {
                    val response = RetrofitClient.authApi.getBookingsByUserId(userId)
                    if (response.isSuccessful) {
                        val bookings = response.body() ?: emptyList()
                        recyclerView.adapter = BookingAdapter(bookings)
                    } else {
                        Toast.makeText(applicationContext, "Failed to load bookings", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}