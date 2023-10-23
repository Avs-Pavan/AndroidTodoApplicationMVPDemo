package com.kevin.androidtodoapplicationmvpdemo.list.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.kevin.androidtodoapplicationmvpdemo.add.view.AddTodoActivity
import com.kevin.androidtodoapplicationmvpdemo.databinding.ActivityMainBinding
import com.kevin.androidtodoapplicationmvpdemo.edit.view.EditTodoActivity
import com.kevin.androidtodoapplicationmvpdemo.list.contract.TodoListContract
import com.kevin.androidtodoapplicationmvpdemo.list.model.TodoListModel
import com.kevin.androidtodoapplicationmvpdemo.list.presenter.TodoListPresenter
import com.kevin.androidtodoapplicationmvpdemo.list.util.recyclerview.RecyclerListener
import com.kevin.androidtodoapplicationmvpdemo.list.util.recyclerview.TodoListAdapter
import com.kevin.androidtodoapplicationmvpdemo.util.room.room.database.TodoDataBase
import com.kevin.androidtodoapplicationmvpdemo.util.room.room.entity.TodoItem

class TodoListActivity : AppCompatActivity(), TodoListContract.View {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val todoList = mutableListOf<TodoItem>()
    private lateinit var adapter: TodoListAdapter
    private lateinit var presenter: TodoListContract.Presenter

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

        // Initialize presenter
        presenter = TodoListPresenter(TodoListModel(db))
        // Attach view to presenter
        presenter.onAttach(this)

        initViews()
    }

    override fun showTodoList(todoList: List<TodoItem>) {
        adapter.updateList(todoList)
    }


    override fun initViews() {
        binding.addBtn.setOnClickListener {
            val intent = Intent(this, AddTodoActivity::class.java)
            startActivity(intent)
        }

        adapter = TodoListAdapter(todoList, object : RecyclerListener {
            override fun onClick(position: Int) {
                // enter edit mode
                val intent = Intent(this@TodoListActivity, EditTodoActivity::class.java)
                intent.putExtra("id", todoList[position].id)
                startActivity(intent)
            }

        })

        binding.recyclerView.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        presenter.onTodoList()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}