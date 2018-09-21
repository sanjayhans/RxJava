package com.panic1k.rxjava.ModelLayer


import com.panic1k.rxjava.ModelLayer.NetworkLayer.NetworkLayer
import com.panic1k.rxjava.ModelLayer.PersistenceLayer.PersistenceLayer

class ModelLayer {

    companion object {
        val shared = ModelLayer()
    }

    private val networkLayer = NetworkLayer.instance
    private val persistenceLayer = PersistenceLayer.shared
}