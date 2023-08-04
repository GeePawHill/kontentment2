package org.geepawhill.kontentment

class JavaFxTimerFactory {
    fun makeTimer(tick: (now: Long) -> Unit): Timer {
        return ClockTimer(tick)
    }
}