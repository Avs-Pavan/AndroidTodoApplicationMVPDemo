package com.kevin.androidtodoapplicationmvpdemo.util.room.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.kevin.androidtodoapplicationmvpdemo.util.room.room.entity.TodoItem


@Dao
interface TodoDao {


    // Query to get all todoitem
    @Query("SELECT * FROM todo_table")
    suspend fun getAll(): List<TodoItem>

    // Query to get todoitem by id
    @Query("SELECT * FROM todo_table WHERE id = :id")
    suspend fun getById(id: Int): TodoItem

    // Insert todoitem to database
    @Insert
    suspend fun insert(todoItem: TodoItem): Long

    // Delete todoitem from database
    @Delete
    suspend fun delete(todoItem: TodoItem): Int

    // Update todoitem in database
    @Update
    suspend fun update(todoItem: TodoItem): Int


}