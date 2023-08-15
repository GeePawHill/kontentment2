package org.geepawhill.kontentment

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ClockTest {

    val timeInMs = mutableListOf<Long>()

    val timer = TestingPulseTimerFactoryAndTimer()
    val clock = Clock(timer) { milliseconds ->
        timeInMs += milliseconds
    }

    @Test
    fun `play starts timer`() {
        clock.play()
        makeTimerTickAt(100)
        assertEquals(1, timer.startsCalled)
    }

    @Test
    fun `sends absolute time`() {
        clock.play()
        makeTimerTickAt(10)
        makeTimerTickAt(30)
        makeTimerTickAt(40)
        assertEquals(0L, timeInMs[0])
        assertEquals(20L, timeInMs[1])
        assertEquals(30L, timeInMs[2])
    }

    @Test
    fun `pause stops timer`() {
        clock.play()
        makeTimerTickAt(100)
        clock.pause()
        makeTimerTickAt(200)
        assertEquals(1, timer.startsCalled)
        assertEquals(1, timer.stopsCalled)
    }

    @Test
    fun `play after pause is correct`() {
        clock.play()
        makeTimerTickAt(100)     // starts at 0
        assertEquals(0L, timeInMs.last())
        makeTimerTickAt(300)     // plays to 200
        assertEquals(200L, timeInMs.last())
        clock.pause()
        makeTimerTickAt(5000)    // produces no clock call
        clock.play()
        makeTimerTickAt(10000)   // resumes at 200
        assertEquals(200L, timeInMs.last())
        makeTimerTickAt(10100)   // advances to 300
        assertEquals(300L, timeInMs.last())
    }

    @Test
    fun `double play is no op`() {
        clock.play()
        makeTimerTickAt(100)
        clock.play()
        makeTimerTickAt(200)
        assertEquals(1, timer.startsCalled)
    }

    @Test
    fun `double pause is no op`() {
        clock.play()
        makeTimerTickAt(100)
        clock.pause()
        makeTimerTickAt(1000)
        clock.pause()
        makeTimerTickAt(2000)
        assertEquals(1, timer.stopsCalled)
    }


    @Test
    fun `starts paused at zero`() {
        clock.play()
        makeTimerTickAt(100)
        assertEquals(1, timeInMs.size)
        assertEquals(0L, timeInMs[0])
    }

    @Test
    fun `seek sets time`() {
        clock.play()
        makeTimerTickAt(100)
        clock.seek(30000L)
        makeTimerTickAt(1000)
        assertEquals(30000L, timeInMs.last())
        clock.seek(2000L)
        makeTimerTickAt(1000)
        assertEquals(2000L, timeInMs.last())
    }


    private fun makeTimerTickAt(ms: Int) {
        timer.tick(ms.toLong() * 1000000L)
    }

}