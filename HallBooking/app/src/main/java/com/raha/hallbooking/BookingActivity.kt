package com.raha.hallbooking

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.raha.hallbooking.api.RetrofitClient
import com.raha.hallbooking.model.Booking
import com.raha.hallbooking.model.Hall
import com.raha.hallbooking.model.User
import kotlinx.coroutines.*

class BookingActivity : AppCompatActivity() {
    private lateinit var hallSpinner: Spinner
    private lateinit var dateInput: EditText
    private lateinit var timeInput: EditText
    private lateinit var notesInput: EditText
    private lateinit var submitBtn: Button

    private val coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private var hallList: List<Hall> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val userId = intent.getIntExtra("userId", -1)

        hallSpinner = findViewById(R.id.hallSpinner)
        dateInput = findViewById(R.id.dateInput)
        timeInput = findViewById(R.id.timeInput)
        notesInput = findViewById(R.id.purposeInput)
        submitBtn = findViewById(R.id.submitBtn)

        loadHalls()

        submitBtn.setOnClickListener {
            val selectedHall = hallList.getOrNull(hallSpinner.selectedItemPosition)
            val date = dateInput.text.toString()
            val time = timeInput.text.toString()
            val note = notesInput.text.toString()

            if (selectedHall == null || date.isEmpty() || time.isEmpty() || note.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val userId = intent.getIntExtra("userId", -1)
            val booking = Booking(
                id = 0,
                hall = selectedHall,
                bookingDate = date,
                timeSlot = time,
                notes = note,
                user = User(id = userId)
            )

            coroutineScope.launch {
                try {
                    val response = RetrofitClient.authApi.createBooking(booking)
                    if (response.isSuccessful) {
                        Toast.makeText(applicationContext, "Booking successful!", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Booking failed", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun loadHalls() {
        coroutineScope.launch {
            try {
                val response = RetrofitClient.authApi.getAllHalls()
                if (response.isSuccessful) {
                    hallList = response.body() ?: emptyList()
                    val hallNames = hallList.map { it.name }
                    val adapter = ArrayAdapter(this@BookingActivity, android.R.layout.simple_spinner_dropdown_item, hallNames)
                    hallSpinner.adapter = adapter
                } else {
                    Toast.makeText(applicationContext, "Failed to load halls", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "Error loading halls: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}