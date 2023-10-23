package com.kevin.androidtodoapplicationmvpdemo.util.room.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kevin.androidtodoapplicationmvpdemo.util.room.room.dao.TodoDao
import com.kevin.androidtodoapplicationmvpdemo.util.room.room.entity.TodoItem


@Database(entities = [TodoItem::class], version = 1)
abstract class TodoDataBase() : RoomDatabase() {
    // Abstract method to get PersonDao
    abstract fun toDao(): TodoDao
}