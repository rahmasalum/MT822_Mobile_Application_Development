package com.example.lifecycle2

import android.app.Activity
import android.os.Bundle
import android.content.res.Configuration
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private lateinit var tvMessage: TextView
    private var counter = 0
    private val lifecycleLog = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showToast("onCreate")
        setContentView(R.layout.activity_main)

        tvMessage = findViewById(R.id.displaytext)

        tvMessage.setOnClickListener {
            counter++
            logAndDisplay("You clicked $counter times")
        }

        // Restore previous state if available
        savedInstanceState?.let {
            counter = it.getInt("counter", 0)
            logAndDisplay("You clicked $counter times")
        }
    }

    private fun logAndDisplay(message: String) {
        lifecycleLog.append("$message\n")
        tvMessage.text = lifecycleLog.toString()
    }

    override fun onStart() {
        super.onStart()
        showToast("onStart")
    }

    override fun onResume() {
        super.onResume()
        showToast("onResume")
    }

    override fun onPause() {
        super.onPause()
        showToast("onPause")
    }

    override fun onStop() {
        super.onStop()
        showToast("onStop")
    }

    override fun onRestart() {
        super.onRestart()
        showToast("onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        showToast("onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter", counter)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> logAndDisplay("Orientation changed to Landscape")
            Configuration.ORIENTATION_PORTRAIT -> logAndDisplay("Orientation changed to Portrait")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
