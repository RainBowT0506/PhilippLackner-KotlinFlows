package com.rainbowt.philipplackner_kotlinflows

import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var dispatchers: TestDispatchers

    @Before
    fun setUp() {
        dispatchers = TestDispatchers()
        mainViewModel = MainViewModel(dispatchers)
    }

    @Test
    fun `countDownFlow, properly counts down from 5 to 0`() = runBlocking {
        mainViewModel.countDownFlow.test {
            for (i in 5 downTo 0) {
                dispatchers.testDispatcher.scheduler.advanceTimeBy(1000L)
                val emission = awaitItem()
                assertThat(emission).isEqualTo(i)
            }
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `squareNumber, number properly squared`() = runBlocking {
        dispatchers.testDispatcher.scheduler.advanceTimeBy(1000L)

        val job = launch {
            mainViewModel.sharedFlow.test {
                val emission = awaitItem()
                assertThat(emission).isEqualTo(9)
                cancelAndConsumeRemainingEvents()
            }
        }
        mainViewModel.squareNumber(3)
        job.join()
        job.cancel()
    }
}