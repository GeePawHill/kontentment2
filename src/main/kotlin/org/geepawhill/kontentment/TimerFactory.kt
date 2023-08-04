package org.geepawhill.kontentment

interface TimerFactory {
    fun makeTimer(tick: (now: Long) -> Unit): Timer
}