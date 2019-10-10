package com.panic1k.rxjava

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.panic1k.rxjava.common.fromJson
import com.panic1k.rxjava.modellayer.PersistenceLayer.LocalDatabase
import com.panic1k.rxjava.modellayer.PersistenceLayer.PersistenceLayer
import com.panic1k.rxjava.modellayer.PersistenceLayer.PhotoDescription
import com.panic1k.rxjava.simplexamples.SimpleRx
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RxJavaApplication: Application() {

    companion object {
        lateinit var database: LocalDatabase
    }

    override fun onCreate() {
        super.onCreate()

        println("Simple App being used.")

        setupDatabase()

        //SimpleRx.simpleValues()
      SimpleRx.subjects()
    }

    fun setupDatabase(){
        database = Room.databaseBuilder(this, LocalDatabase::class.java, "LearningRxJavaLocalDatabase").build()

        GlobalScope.launch {
            val photoDescriptions = loadJson()
            PersistenceLayer.shared.prepareDb(photoDescriptions)
        }
    }


    fun loadJson(): List<PhotoDescription> {
        val json = loadDescriptionsFromFile()
        val photoDescriptions = Gson().fromJson<List<PhotoDescription>>(json)
        return photoDescriptions
    }

    private fun loadDescriptionsFromFile(): String {
        //ignoring IOExceptions
        val inputStream = assets.open("PhotoDescription.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        return json
    }
}