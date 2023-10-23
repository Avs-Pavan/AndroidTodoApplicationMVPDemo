package com.kevin.androidtodoapplicationmvpdemo.edit.presenter

import com.kevin.androidtodoapplicationmvpdemo.edit.contract.EditContract
import com.kevin.androidtodoapplicationmvpdemo.util.room.room.entity.TodoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditTodoPresenter(private val model: EditContract.Model) : EditContract.Presenter {

    private var view: EditContract.View? = null

    override fun onAttach(view: EditContract.View) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }

    override fun updateTodo(item: TodoItem) {
        CoroutineScope(Dispatchers.Main).launch {
            model.updateTodoItem(item)
            view?.onSuccess()
        }
    }

    override fun getTodoItem(id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val item = model.getTodoItem(id)
            view?.onTodoItemRetrieved(item)
        }
    }
}