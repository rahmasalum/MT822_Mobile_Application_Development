package com.raha.hallbooking
import android.widget.Button
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.logBtn).setOnClickListener {

            startActivity(Intent(this,LoginActivity::class.java))
        }

    }
}