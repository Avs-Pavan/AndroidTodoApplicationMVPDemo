package com.kevin.androidtodoapplicationmvpdemo.list.presenter

import com.kevin.androidtodoapplicationmvpdemo.list.contract.TodoListContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TodoListPresenter(private var model: TodoListContract.Model) : TodoListContract.Presenter {

    private var view: TodoListContract.View? = null

    override fun onAttach(view: TodoListContract.View) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }


    override fun onTodoList() {
        CoroutineScope(Dispatchers.Main).launch {
            view?.showTodoList(model.getTodoList())
        }

    }
}