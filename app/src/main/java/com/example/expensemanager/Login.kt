package com.example.expensemanager

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.expensemanager.DbHelper.dbHelper

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val userName = findViewById<EditText>(R.id.userName)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.btnlog)
        val sign = findViewById<TextView>(R.id.signtv)

        val preferences : SharedPreferences.Editor

        preferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE).edit()
        val db: dbHelper = dbHelper(this)

        sign.setOnClickListener {
            val intent= Intent(this,SignIn::class.java)
            startActivity(intent)


        }
        login.setOnClickListener {
            val name:String = userName.text.toString()
            val pswrd:String = password.text.toString()


            val authentication: Boolean = db.getData(name, pswrd)
            if (authentication)
            {
                preferences.putString("Name",name);
                preferences.putString("Password",pswrd);
                preferences.apply();
                preferences.commit()

                val intent= Intent(this,UsersList::class.java)
                startActivity(intent)


            }

        }

    }
}