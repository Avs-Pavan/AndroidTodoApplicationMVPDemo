package com.kevin.androidtodoapplicationmvpdemo.edit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.kevin.androidtodoapplicationmvpdemo.R
import com.kevin.androidtodoapplicationmvpdemo.databinding.ActivityEditTodoBinding
import com.kevin.androidtodoapplicationmvpdemo.edit.contract.EditContract
import com.kevin.androidtodoapplicationmvpdemo.edit.model.EditTodoModel
import com.kevin.androidtodoapplicationmvpdemo.edit.presenter.EditTodoPresenter
import com.kevin.androidtodoapplicationmvpdemo.util.room.room.database.TodoDataBase
import com.kevin.androidtodoapplicationmvpdemo.util.room.room.entity.TodoItem

class EditTodoActivity : AppCompatActivity(), EditContract.View {

    private val binding by lazy { ActivityEditTodoBinding.inflate(layoutInflater) }

    private lateinit var item: TodoItem

    private lateinit var presenter: EditContract.Presenter
    private val db by lazy {
        Room.databaseBuilder(
            this.applicationContext,
            TodoDataBase::class.java,
            "todo_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter = EditTodoPresenter(EditTodoModel(db))
        presenter.onAttach(this)

        // Get the id of the todo item to be edited
        val id = intent.getIntExtra("id", -1)

        // Get the todo item from the database
        presenter.getTodoItem(id)

    }

    override fun initViews() {
        binding.apply {
            titleEt.setText(item.title)
            descriptionEt.setText(item.description)
            submitBtn.setOnClickListener {
                val title = titleEt.text.toString()
                val description = descriptionEt.text.toString()
                if (title.isEmpty() || description.isEmpty()) {
                    toast("Please fill in all fields")
                    return@setOnClickListener
                }
                item.title = title
                item.description = description
                presenter.updateTodo(item)
            }
        }

        if (item.status!!)
            binding.completedBtn.text = "Mark as incomplete"
        else
            binding.completedBtn.text = "Mark as complete"

        binding.completedBtn.setOnClickListener {
            item.status = !item.status!!
            presenter.updateTodo(item)
        }
    }

    override fun onTodoItemRetrieved(item: TodoItem) {
        this.item = item

        // Initialize views
        initViews()
    }

    override fun onError(error: String) {
        toast(error)
    }

    override fun onSuccess() {
        toast("Todo item added successfully")
        finish()
    }


    private fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}