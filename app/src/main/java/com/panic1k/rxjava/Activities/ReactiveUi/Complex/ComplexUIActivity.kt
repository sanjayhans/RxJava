package com.panic1k.rxjava.Activities.ReactiveUi.Complex

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.panic1k.rxjava.R




private enum class CellType {
    ITEM,
    ITEM2
}

class ReactiveUIActivity : AppCompatActivity() {

    private val dataSet = (0..100).toList().map { it.toString() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complex_ui)
    }
}