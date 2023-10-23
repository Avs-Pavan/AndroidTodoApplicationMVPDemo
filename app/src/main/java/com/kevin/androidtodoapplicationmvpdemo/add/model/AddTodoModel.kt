package com.kevin.androidtodoapplicationmvpdemo.add.model

import com.kevin.androidtodoapplicationmvpdemo.add.contract.AddContract
import com.kevin.androidtodoapplicationmvpdemo.util.room.room.database.TodoDataBase
import com.kevin.androidtodoapplicationmvpdemo.util.room.room.entity.TodoItem

class AddTodoModel(private val dataBase: TodoDataBase) : AddContract.Model {
    override suspend fun addTodoItem(title: String, description: String) {
        dataBase.toDao().insert(TodoItem(0, title, description))
    }
}