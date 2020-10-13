package fr.sohayb.marvelapp.app

import fr.sohayb.marvelapp.base.domain.ProcessorResultCallback
import fr.sohayb.marvelapp.base.domain.ResultType

class MockProcessorResultCallback: ProcessorResultCallback {

    val results = mutableListOf<ResultType>()

    override fun invoke(p1: ResultType) {
        results.add(p1)
    }
}