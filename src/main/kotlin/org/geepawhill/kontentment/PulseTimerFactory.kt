package org.geepawhill.kontentment

interface PulseTimerFactory {
    fun makePulseTimer(pulseHandler: (ns: Long) -> Unit): PulseTimer
}