package org.geepawhill.kontentment.core

interface PulseTimerFactory {
    fun makePulseTimer(pulseHandler: (ns: Long) -> Unit): PulseTimer
}