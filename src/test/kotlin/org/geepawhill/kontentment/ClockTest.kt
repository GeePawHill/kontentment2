package org.geepawhill.kontentment

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ClockTest {

    val calls = mutableListOf<Double>()

    val factory = TestTimerFactory()
    val clock = Clock(factory) { seconds ->
        calls += seconds
    }

    @Test
    fun `clock deltas at small time`() {
        clock.start()
        assertTrue(calls.isEmpty())
        factory.tick(1000)
        assertFalse(calls.isEmpty())
    }
}