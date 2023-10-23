package com.kevin.androidtodoapplicationmvpdemo.edit.contract

import com.kevin.androidtodoapplicationmvpdemo.util.room.room.entity.TodoItem

interface EditContract {

    interface View {
        fun onError(error: String)
        fun onSuccess()
        fun initViews()
        fun onTodoItemRetrieved(item: TodoItem)
    }

    interface Presenter {
        fun onAttach(view: View)
        fun onDetach()
        fun updateTodo(item: TodoItem)

        fun getTodoItem(id: Int)
    }

    interface Model {
        suspend fun updateTodoItem(item: TodoItem)

        suspend fun getTodoItem(id: Int): TodoItem
    }


}