package com.kevin.androidtodoapplicationmvpdemo.add.contract

interface AddContract {

    interface View {
        fun onError(error: String)
        fun onSuccess()
        fun initViews()
    }

    interface Presenter {

        fun onAttach(view: View)
        fun onDetach()
        fun addTodoItem(title: String, description: String)
    }

    interface Model {
        suspend fun addTodoItem(title: String, description: String)
    }


}