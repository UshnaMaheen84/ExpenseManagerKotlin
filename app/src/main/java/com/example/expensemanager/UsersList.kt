package com.example.expensemanager

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.DbHelper.dbHelper
import com.example.expensemanager.models.UserModel

class UsersList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)

        val arrayList: ArrayList<UserModel> = ArrayList()
        val db: dbHelper = dbHelper(this)
        val preferences : SharedPreferences.Editor

        preferences = getSharedPreferences("SharedPreferences", MODE_PRIVATE).edit()


        arrayList.addAll(db.viewUser())

        val recyclerview = findViewById<RecyclerView>(R.id.r_view)

        val adapter = UserAdapter(arrayList,this)
        recyclerview.adapter = adapter
        recyclerview.layoutManager= LinearLayoutManager(this)


        val btn = findViewById<Button>(R.id.logout)
        btn.setOnClickListener {
            preferences.clear()
            preferences.apply()
            val intent = Intent(this, Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()

        }

    }
}