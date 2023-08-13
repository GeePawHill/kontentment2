package org.geepawhill.kontentment

interface PulseTimerFactory {
    fun makePulseTimer(tick: (now: Long) -> Unit): PulseTimer
}