package org.geepawhill.kontentment

class JavaFxPulseTimerFactory : PulseTimerFactory {
    override fun makePulseTimer(tick: (now: Long) -> Unit): PulseTimer {
        return JavaFxPulseTimer(tick)
    }
}