package com.kevin.androidtodoapplicationmvpdemo.add.presenter

import com.kevin.androidtodoapplicationmvpdemo.add.contract.AddContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddTodoPresenter(private val model: AddContract.Model) : AddContract.Presenter {

    private var view: AddContract.View? = null
    override fun onAttach(view: AddContract.View) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }


    override fun addTodoItem(title: String, description: String) {
        if (title.isEmpty() || description.isEmpty()) {
            view?.onError("Title or Description cannot be empty")
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                model.addTodoItem(title, description)
                view?.onSuccess()
            }
        }
    }
}