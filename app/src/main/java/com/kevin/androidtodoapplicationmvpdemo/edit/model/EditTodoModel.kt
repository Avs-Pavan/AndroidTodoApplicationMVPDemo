package com.kevin.androidtodoapplicationmvpdemo.edit.model

import com.kevin.androidtodoapplicationmvpdemo.edit.contract.EditContract
import com.kevin.androidtodoapplicationmvpdemo.util.room.room.database.TodoDataBase
import com.kevin.androidtodoapplicationmvpdemo.util.room.room.entity.TodoItem

class EditTodoModel(private val dataBase: TodoDataBase) : EditContract.Model {

    override suspend fun updateTodoItem(item: TodoItem) {
        dataBase.toDao().update(item)
    }

    override suspend fun getTodoItem(id: Int): TodoItem {
        return dataBase.toDao().getById(id)
    }
}