package com.raha.hallbooking.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.raha.hallbooking.R
import com.raha.hallbooking.model.Booking

class BookingAdapter(private val bookings: List<Booking>) :
    RecyclerView.Adapter<BookingAdapter.BookingViewHolder>() {

    class BookingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hallName: TextView = itemView.findViewById(R.id.hallName)
        val dateTime: TextView = itemView.findViewById(R.id.dateTime)
        val purpose: TextView = itemView.findViewById(R.id.purpose)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_booking, parent, false)
        return BookingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val booking = bookings[position]
        holder.hallName.text = booking.hall.name
        holder.dateTime.text = "${booking.bookingDate} | ${booking.timeSlot}"
        holder.purpose.text = booking.notes
    }

    override fun getItemCount() = bookings.size
}
