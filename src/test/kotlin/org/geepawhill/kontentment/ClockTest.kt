package org.geepawhill.kontentment

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class ClockTest {

    val calls = mutableListOf<Long>()

    val timer = TestTimerFactory()
    val clock = Clock(timer) { tenths ->
        calls += tenths
    }

    @Test
    fun `play starts timer`() {
        clock.play()
        handle(100)
        assertEquals(1, timer.startsCalled)
    }

    @Test
    fun `pause stops timer`() {
        clock.play()
        handle(100)
        clock.pause()
        handle(200)
        assertEquals(1, timer.startsCalled)
        assertEquals(1, timer.stopsCalled)
    }

    @Test
    fun `play after pause is correct`() {
        clock.play()
        handle(100)     // starts at 0
        assertEquals(0L, calls.last())
        handle(300)     // plays to 200
        assertEquals(200L, calls.last())
        clock.pause()
        handle(5000)    // produces no tick
        clock.play()
        handle(10000)   // resumes at 200
        assertEquals(200L, calls.last())
        handle(10100)   // advances to 300
        assertEquals(300L, calls.last())
    }

    @Test
    fun `double play is no op`() {
        clock.play()
        handle(100)
        clock.play()
        handle(200)
        assertEquals(1, timer.startsCalled)
    }

    @Disabled("Not ready yet")
    @Test
    fun `double pause is no op`() {
        clock.play()
        clock.pause()
        clock.pause()
        assertEquals(1, timer.stopsCalled)
    }


    @Test
    fun `starts paused at zero`() {
        clock.play()
        handle(100)
        assertEquals(1, calls.size)
        assertEquals(0L, calls[0])
    }

    @Test
    fun `seek sets time`() {
        clock.play()
        handle(100)
        clock.seek(30000L)
        handle(1000)
        assertEquals(30000L, calls.last())
        clock.seek(2000L)
        handle(1000)
        assertEquals(2000L, calls.last())
    }

    @Test
    fun `sends absolute time`() {
        clock.play()
        handle(10)
        handle(30)
        handle(40)
        assertEquals(0L, calls[0])
        assertEquals(20L, calls[1])
        assertEquals(30L, calls[2])
    }

    private fun handle(ms: Int) {
        clock.handle(msToNanos(ms.toLong()))
    }

    private fun msToNanos(ms: Long): Long = ms * 1000000L
}