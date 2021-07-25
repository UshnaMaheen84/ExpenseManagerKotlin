package com.example.expensemanager

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.expensemanager.DbHelper.dbHelper
import com.example.expensemanager.models.UserModel

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val preferences : SharedPreferences.Editor

        preferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE).edit()


        val userName = findViewById<EditText>(R.id.name)
        val password = findViewById<EditText>(R.id.mypswrd)
        val signin = findViewById<Button>(R.id.buttonsignin)

        signin.setOnClickListener {
            val name:String = userName.text.toString()
            val pswrd:String = password.text.toString()

            preferences.putString("Name",name)
            preferences.putString("Password",pswrd)
            preferences.apply()
            preferences.commit()

            val db: dbHelper= dbHelper(this)
            val status = db.addUser(UserModel(name, pswrd))

            if(status > -1){
                Toast.makeText(applicationContext,"record save",Toast.LENGTH_LONG).show()

                val intent= Intent(this,UsersList::class.java)
                startActivity(intent)

            }

        else{
            Toast.makeText(applicationContext,"name or email cannot be blank",Toast.LENGTH_LONG).show()
        }

        }

    }
}