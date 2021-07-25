package com.example.expensemanager

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        StartApp()

        Handler().postDelayed({
                     }, 2000)

    }

    fun StartApp(){

        val preferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE)

        val name = preferences.getString("Name", null)
        val password = preferences.getString("Password", null)

        if (name != null && password != null) {
            val intent= Intent(this,UsersList::class.java)
            startActivity(intent)
            finish();

        }
        else {
            val intent = Intent(this, Login::class.java);
            startActivity(intent);
            finish();

        }

        }
}