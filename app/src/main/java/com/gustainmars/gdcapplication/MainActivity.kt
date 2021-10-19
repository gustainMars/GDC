package com.gustainmars.gdcapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gustainmars.gdcapplication.feature.data.entity.TaskDto
import com.gustainmars.gdcapplication.feature.presentation.TaskListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gdc)

        viewModel = ViewModelProvider (
            this,
            TaskListViewModel.TaskViewModelFactory(GdcApplication.instance)
        ).get(TaskListViewModel::class.java)

        viewModel.alltasks.observe(this, {
            Log.d("MyTaskList", it.toString())
            Toast.makeText(this, it.size.toString(), Toast.LENGTH_LONG).show()
        })

        viewModel.addTask(TaskDto(name = "Test"))
    }
}