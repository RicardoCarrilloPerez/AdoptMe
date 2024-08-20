package com.example.adoptme

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "AdoptionDB.db"
        private const val TABLE_NAME = "Adoptante"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_ADDRESS = "address"
        private const val COLUMN_PHONE = "phone"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_ADDRESS + " TEXT,"
                + COLUMN_PHONE + " TEXT" + ")")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun addAdoptante(name: String, address: String, phone: String, animalName:String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_ADDRESS, address)
            put(COLUMN_PHONE, phone)
            put("animal_name", animalName)
        }
        val success = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return success
    }

    fun getAllAdoptantes(): Cursor? {
        val db = this.readableDatabase
        // Selecciona la columna id como _id para que SimpleCursorAdapter funcione correctamente
        return db.rawQuery("SELECT $COLUMN_ID AS _id, $COLUMN_NAME, $COLUMN_ADDRESS, $COLUMN_PHONE FROM $TABLE_NAME", null)
    }

}
