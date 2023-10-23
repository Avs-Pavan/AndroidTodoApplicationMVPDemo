package com.kevin.androidtodoapplicationmvpdemo.list.util.recyclerview

import android.text.Spannable
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kevin.androidtodoapplicationmvpdemo.databinding.TodoRowBinding
import com.kevin.androidtodoapplicationmvpdemo.util.room.room.entity.TodoItem


class TodoListAdapter(
    private val todoItemList: MutableList<TodoItem>,
    private val listener: RecyclerListener
) :
    RecyclerView.Adapter<TodoListAdapter.PersonViewHolder>() {

    // Inner class to hold views
    inner class PersonViewHolder(val binding: TodoRowBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = TodoRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return todoItemList.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {

        // Get item at current position
        val todo = todoItemList[position]

        // Bind data with view
        holder.binding.description.text = todo.description

        if (todo.status == true) {
            val spannableString = SpannableString(todo.title)
            spannableString.setSpan(
                StrikethroughSpan(),
                0,
                spannableString.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            holder.binding.title.text = spannableString
        } else {
            holder.binding.title.text = todo.title
        }


        // Set click listener
        holder.binding.root.setOnClickListener {
            listener.onClick(position)
        }
    }


    fun updateList(newList: List<TodoItem>) {
        todoItemList.clear()
        todoItemList.addAll(newList)
        notifyDataSetChanged()
    }


    override fun getItemId(position: Int): Long {
        return todoItemList[position].id.toLong()
    }
}