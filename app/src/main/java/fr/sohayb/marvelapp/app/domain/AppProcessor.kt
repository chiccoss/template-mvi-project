package fr.sohayb.marvelapp.app.domain

import fr.sohayb.marvelapp.base.domain.ActionType
import fr.sohayb.marvelapp.base.domain.ProcessorResultCallback
import fr.sohayb.marvelapp.base.domain.ProcessorType

class AppProcessor(private val processors: List<ProcessorType>): ProcessorType {

    override suspend fun process(action: ActionType, next: ProcessorResultCallback) {
        for (processor in processors) {
            processor.process(action, next)
        }
    }
}