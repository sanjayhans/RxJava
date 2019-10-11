package com.panic1k.rxjava.activities.tasksexample


import com.panic1k.rxjava.modellayer.entities.Person
import com.panic1k.rxjava.modellayer.ModelLayer

class TasksExamplePresenter {

    private val modelLayer = ModelLayer.shared
    private val people = listOf(Person("Norris",  "Najar",     0),
                                Person("Dylan",   "Decarlo",   1),
                                Person("Sonny",   "Stecher",   2),
                                Person("Napoleon","Nicols",    3),
                                Person("Jinny",   "Jordahl",   4),
                                Person("Wendi",   "Woodhouse", 5))
}