package com.rainbowt.philipplackner_kotlinflows

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler

@ExperimentalCoroutinesApi
class TestDispatchers(scheduler: TestCoroutineScheduler = TestCoroutineScheduler()) : DispatcherProvider {
    val testDispatcher = StandardTestDispatcher(scheduler)

    override val main: CoroutineDispatcher
        get() = testDispatcher

    override val io: CoroutineDispatcher
        get() = testDispatcher

    override val default: CoroutineDispatcher
        get() = testDispatcher
}
