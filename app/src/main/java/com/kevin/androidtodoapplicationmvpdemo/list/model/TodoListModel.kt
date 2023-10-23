package com.kevin.androidtodoapplicationmvpdemo.list.model

import com.kevin.androidtodoapplicationmvpdemo.list.contract.TodoListContract
import com.kevin.androidtodoapplicationmvpdemo.util.room.room.database.TodoDataBase
import com.kevin.androidtodoapplicationmvpdemo.util.room.room.entity.TodoItem

class TodoListModel(private val dataBase: TodoDataBase) : TodoListContract.Model {
    override suspend fun getTodoList(): List<TodoItem> {
        return dataBase.toDao().getAll()
    }
}