package com.panic1k.rxjava.activities.databaseexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.panic1k.rxjava.activities.databaseexample.Recycler.PhotoDescriptionViewAdapter
import com.panic1k.rxjava.R
import kotlinx.android.synthetic.main.activity_database_example.*


class DatabaseExampleActivity : AppCompatActivity() {

    private val presenter = DatabaseExamplePresenter()

    lateinit var adapter: PhotoDescriptionViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_example)

        attachUI()
    }

    private fun attachUI() {
        val linearLayoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        photoDescriptionRecyclerView.setHasFixedSize(true)
        photoDescriptionRecyclerView.layoutManager = linearLayoutManager
        photoDescriptionRecyclerView.addItemDecoration(dividerItemDecoration)

        initializeListView()
    }

    private fun initializeListView() {
        adapter = PhotoDescriptionViewAdapter { view, position -> rowTapped(position) }
        photoDescriptionRecyclerView.adapter = adapter
    }

    private fun rowTapped(position: Int) {
        println("🍄")
        println(adapter.photoDescriptions[position])
    }
}