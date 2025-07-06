package com.raha.hallbooking

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.raha.hallbooking.api.RetrofitClient
import kotlinx.coroutines.*
import retrofit2.HttpException
import com.raha.hallbooking.model.User

class LoginActivity : AppCompatActivity() {
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginBtn: Button
    private lateinit var registerText: TextView

    private val coroutineScope = CoroutineScope(Dispatchers.Main + Job())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        loginBtn = findViewById(R.id.loginBtn)
        registerText = findViewById(R.id.registerText)

        loginBtn.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            coroutineScope.launch {
                try {
                    val response = RetrofitClient.authApi.getUserByEmail(email)
                    if (response.isSuccessful) {
                        val user = response.body()
                        if (user != null && user.password == password) {
                            Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_SHORT).show()


                            val intent = Intent(this@LoginActivity, DashboardActivity::class.java)
                            intent.putExtra("userId", user.id)
                            intent.putExtra("userName", user.fullName)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(applicationContext, "Wrong email or password", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(applicationContext, "User not found", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        registerText.setOnClickListener {
            val url = "http://192.168.58.210:3000/create-account"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }
}