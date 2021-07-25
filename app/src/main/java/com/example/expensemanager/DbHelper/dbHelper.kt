package com.example.expensemanager.DbHelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.expensemanager.models.UserModel

public class dbHelper(
    context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION)
{   companion object {
    private val DATABASE_VERSION = 1
    private val DATABASE_NAME = "UserDatabase"

    private val TABLE_USER = "UserTable"
    private val KEY_ID = "id"
    private val KEY_NAME = "name"
    private val KEY_PASSWORD = "password"
}

    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_USERS_TABLE = ("CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_PASSWORD + " TEXT" + ")")
        db?.execSQL(CREATE_USERS_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_USER)
        onCreate(db)
    }
    //method to insert data
    fun addUser(user: UserModel):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
      //  contentValues.put(KEY_ID, user.id)
        contentValues.put(KEY_NAME, user.name) // ModelClass Name
        Log.e("anss", user.name)

        contentValues.put(KEY_PASSWORD,user.password ) // ModelClass Pswrd

        // Inserting Row
        val id = db.insert(TABLE_USER, null, contentValues)
        Log.e("anss", id.toString())

        //val success = db.insert(TABLE_CONTACTS, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return id
    }
    //
    fun getData(name: String?, password: String?): Boolean {
        // get readable database as we are not inserting anything
        Log.e("getData",name.toString())
        val db = this.readableDatabase
        val Query =
            String.format("SELECT * FROM  $TABLE_USER WHERE $KEY_NAME = $name" )
        val cursor = db.rawQuery(Query, null)
        return if (cursor != null && cursor.count > 0) {
            cursor.close()
            true
        } else {
            false
        }
    }
    //method to read data
    fun viewUser():List<UserModel>{
        val userList:ArrayList<UserModel> = ArrayList<UserModel>()
        val selectQuery = "SELECT  * FROM $TABLE_USER"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var userId: Int
        var userName: String
        var userPass: String
        if (cursor.moveToFirst()) {
            do {
                userName = cursor.getString(cursor.getColumnIndex("name"))
                userPass = cursor.getString(cursor.getColumnIndex("password"))
                val users= UserModel(name = userName, password = userPass)
                userList.add(users)
            } while (cursor.moveToNext())
        }
        return userList
    }




}

