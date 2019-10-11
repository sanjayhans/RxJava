package com.panic1k.rxjava.modellayer


import com.panic1k.rxjava.modellayer.networklayer.NetworkLayer
import com.panic1k.rxjava.modellayer.persistencelayer.PersistenceLayer

class ModelLayer {

    companion object {
        val shared = ModelLayer()
    }

    private val networkLayer = NetworkLayer.instance
    private val persistenceLayer = PersistenceLayer.shared
}