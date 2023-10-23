package com.kevin.androidtodoapplicationmvpdemo.util.room.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * TodoItem Entity
 **/
@Entity(tableName = "todo_table")
data class TodoItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo var title: String?,
    @ColumnInfo var description: String?,
    @ColumnInfo var status: Boolean? = false
)