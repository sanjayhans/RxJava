package com.panic1k.rxjava.activities.threadingexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.panic1k.rxjava.common.disposedBy
import com.panic1k.rxjava.modellayer.entities.Friend
import com.panic1k.rxjava.R
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_threading_example.*


//Schedulers
//https://android.jlelse.eu/rxjava-schedulers-what-when-and-how-to-use-it-6cfc27293add

//Coroutines vs. Threads
//https://stackoverflow.com/questions/43021816/difference-between-thread-and-coroutine-in-kotlin?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa

class ThreadingExampleActivity : AppCompatActivity() {

    private val presenter = ThreadingExamplePresenter()
    private var bag = CompositeDisposable()
    private lateinit var adapter: ArrayAdapter<String>
    private var mainThreadId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_threading_example)

        mainThreadId = Thread.currentThread().id

        setupUI()
        threadingExamples()
    }

    private fun threadingExamples() {
//        threading()
        threading2()
    }




    private fun threading() {
        presenter.friends
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { items ->
                    println("🚦 current thread: ${Thread.currentThread().id}")
                    println("⁉️🌲 is on UI thread: ${Thread.currentThread().id == mainThreadId}")

//                    launch(UI) {
                        updateList(items)
//                    }
                }.disposedBy(bag)
    }

    //discuss ObserveOn vs SubscribeOn
    private fun threading2() {
        val single = getResult()

        single
//                .observeOn(AndroidSchedulers.mainThread())
//                .observeOn(Schedulers.newThread())
//                .subscribeOn(Schedulers.computation())
                .subscribe{ result ->
                    println("🚦 current thread: ${Thread.currentThread().id}")
                    println("⁉️🐢 is on UI thread: ${Thread.currentThread().id == mainThreadId}")
                    print("result: ${result}")
                }.disposedBy(bag)
    }

    fun getResult(): Observable<String> {
        return Observable.create { observer ->
//            launch {
//                delay(3000)

                println("🚦 current thread: ${Thread.currentThread().id}")
                println("⁉️🐖 is on UI thread: ${Thread.currentThread().id == mainThreadId}")

                observer.onNext("some result")
                observer.onComplete()
//            }
        }
    }

    //region Helper Methods

    private fun setupUI() {
        val listItems = presenter.friends.value.map { it.toString() }

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)

        threadingListView.adapter = adapter

    }

    private fun updateList(items: List<Friend>){
            val itemsArray = items.map { it.description }.toTypedArray()

            adapter.clear()
            adapter.addAll(*itemsArray)
            adapter.notifyDataSetChanged()
    }


    //endregion

}

