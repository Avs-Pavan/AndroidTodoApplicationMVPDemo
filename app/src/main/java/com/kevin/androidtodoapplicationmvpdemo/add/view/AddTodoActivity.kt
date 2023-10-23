package com.kevin.androidtodoapplicationmvpdemo.add.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.kevin.androidtodoapplicationmvpdemo.add.contract.AddContract
import com.kevin.androidtodoapplicationmvpdemo.add.model.AddTodoModel
import com.kevin.androidtodoapplicationmvpdemo.add.presenter.AddTodoPresenter
import com.kevin.androidtodoapplicationmvpdemo.databinding.ActivityAddTodoBinding
import com.kevin.androidtodoapplicationmvpdemo.util.room.room.database.TodoDataBase

class AddTodoActivity : AppCompatActivity(), AddContract.View {

    private val binding by lazy { ActivityAddTodoBinding.inflate(layoutInflater) }

    private lateinit var presenter: AddContract.Presenter


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

        presenter = AddTodoPresenter(AddTodoModel(db))

        presenter.onAttach(this)
        initViews()

    }


    override fun initViews() {
        binding.submitBtn.setOnClickListener {
            val title = binding.titleEt.text.toString()
            val description = binding.descriptionEt.text.toString()

            if (title.isEmpty() || description.isEmpty()) {
                toast("Please fill all the fields")
            } else {
                presenter.addTodoItem(title, description)
            }
        }
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