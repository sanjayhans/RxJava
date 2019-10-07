package com.panic1k.rxjava.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.panic1k.rxjava.R


class BasicExampleActivity : AppCompatActivity() {


    //region Life Cycle Events

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_example)
    }

    //endregion
}