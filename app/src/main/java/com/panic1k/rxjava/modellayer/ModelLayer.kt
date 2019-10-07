package com.panic1k.rxjava.modellayer


import com.panic1k.rxjava.modellayer.NetworkLayer.NetworkLayer
import com.panic1k.rxjava.modellayer.PersistenceLayer.PersistenceLayer

class ModelLayer {

    companion object {
        val shared = ModelLayer()
    }

    private val networkLayer = NetworkLayer.instance
    private val persistenceLayer = PersistenceLayer.shared
}