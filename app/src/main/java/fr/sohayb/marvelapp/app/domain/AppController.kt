package fr.sohayb.marvelapp.app.domain

import androidx.lifecycle.MutableLiveData
import fr.sohayb.marvelapp.base.domain.ActionType
import fr.sohayb.marvelapp.base.domain.ResultType
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import timber.log.Timber
import java.util.concurrent.Executors

@UseExperimental(ExperimentalCoroutinesApi::class, FlowPreview::class, InternalCoroutinesApi::class)
class AppController(
    private val processor: AppProcessor,
    private val reducer: AppReducer,
    initialState: AppState
) : CoroutineScope by CoroutineScope(Executors.newSingleThreadExecutor().asCoroutineDispatcher()) {

    companion object {
        private const val RESULT_CAPACITY = 10
    }

    val state = MutableLiveData<AppState>().apply {
        value = initialState
    }

    private val results = BroadcastChannel<ResultType>(RESULT_CAPACITY)

    init {
        launch {
            results.asFlow()
                .collect { result ->
                    Timber.d("TEST -> receiving result: $result on thread: ${Thread.currentThread()}")
                    val v = state.value ?: return@collect
                    val newState = reducer.reduce(result, v)
                    withContext(Dispatchers.Main) {
                        Timber.d("TEST -> setting value: $newState on thread: ${Thread.currentThread()}")
                        state.value = newState
                    }
                }
        }
    }

    fun dispatch(action: ActionType) {
        launch {
            Timber.d("TEST -> processing action: $action on thread: ${Thread.currentThread()}")
            processor.process(action) { result ->
                Timber.d("TEST -> offering result: $result on thread: ${Thread.currentThread()}")
                results.offer(result)
            }
        }
    }
}