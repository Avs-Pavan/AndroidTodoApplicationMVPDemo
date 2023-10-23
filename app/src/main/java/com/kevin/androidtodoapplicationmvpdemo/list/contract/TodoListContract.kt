package com.kevin.androidtodoapplicationmvpdemo.list.contract

import com.kevin.androidtodoapplicationmvpdemo.util.room.room.entity.TodoItem

sealed interface TodoListContract {
    interface View {
        fun showTodoList(todoList: List<TodoItem>)

        fun initViews()
    }

    interface Model {
        suspend fun getTodoList(): List<TodoItem>
    }

    interface Presenter {

        fun onAttach(view: View)
        fun onDetach()
        fun onTodoList()
    }
}