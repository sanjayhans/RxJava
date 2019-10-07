package com.panic1k.rxjava.activities.tasksexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.panic1k.rxjava.R

class TasksExampleActivity : AppCompatActivity() {

    private val presenter = TasksExamplePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks_example)
    }
}

