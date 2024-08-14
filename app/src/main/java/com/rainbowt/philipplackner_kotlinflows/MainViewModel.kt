package com.rainbowt.philipplackner_kotlinflows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val countDownFlow = flow<Int> {
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue)

        while (currentValue > 0) {
            delay(1000)
            currentValue--
            emit(currentValue)
        }
    }

    init {
        collectFlowWithCount()
        collectFlowWithReduce()
        collectFlowWithFold()
        collectFlowWithFlatMapConcat()
        collectFlowWithFlat()
    }

    private fun collectFlowWithCount() {
        viewModelScope.launch {
            val count = countDownFlow
                .filter { time ->
                    time % 2 == 0
                }
                .map { time ->
                    time * time
                }
                .onEach { time ->
                    println(time)
                }
                .count {
                    it % 2 == 0
                }

            println("The count is $count")
        }
    }

    private fun collectFlowWithReduce() {
        viewModelScope.launch {
            val reduceResult = countDownFlow
                .reduce { accumulator, value ->
                    accumulator + value
                }


            println("Reduce - The sum of squares is $reduceResult")
        }
    }

    private fun collectFlowWithFold() {
        viewModelScope.launch {
            val foldResult = countDownFlow
                .fold(100) { accumulator, value ->
                    accumulator + value
                }

            println("Fold - The sum of squares is $foldResult")
        }
    }

    private fun collectFlowWithFlatMapConcat() {
        val flow1 = flow {
            emit(1)
            delay(1000)
            emit(2)
        }

        viewModelScope.launch {
            flow1.flatMapConcat { value ->
                flow {
                    emit(value + 1)
                    delay(1000)
                    emit(value + 2)
                }
            }.collect { value ->
                println("FlatMapConcat - The value is $value")
            }
        }
    }

    private fun collectFlowWithFlat() {
        val flow = flow {
            delay(250L)
            emit("Appetizer")
            delay(1000L)
            emit("Main dish")
            delay(100L)
            emit("Dessert")
        }

        viewModelScope.launch {
            flow.onEach {
                println("FLOW: $it is delivered")
            }
                .buffer()
                .conflate()
                .collectLatest {
                    println("FLOW: Now eating $it")
                    delay(1500L)
                    println("FLOW: Finished eating $it")
                }
        }
    }
}