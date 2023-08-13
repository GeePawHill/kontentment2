package org.geepawhill.kontentment

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ClockTest {

    val calls = mutableListOf<Long>()

    val timer = TestTimerFactory()
    val clock = Clock(timer) { tenths ->
        calls += tenths
    }

    @Test
    fun `starts at zero`() {
        clock.play()
        timer.tick(100000000L)
        assertEquals(1, calls.size)
        assertEquals(0L, calls[0])
    }

    @Test
    fun `sends absolute time`() {
        clock.play()
        timer.tick(msToNanos(10L))
        timer.tick(msToNanos(30L))
        timer.tick(msToNanos(40L))
        assertEquals(20L, calls[1])
        assertEquals(30L, calls[2])
    }

    private fun msToNanos(ms: Long): Long = ms * 1000000L
}