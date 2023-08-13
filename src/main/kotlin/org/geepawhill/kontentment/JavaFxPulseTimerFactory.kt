package org.geepawhill.kontentment

class JavaFxPulseTimerFactory : PulseTimerFactory {
    override fun makePulseTimer(pulseHandler: (ns: Long) -> Unit): PulseTimer {
        return JavaFxPulseTimer(pulseHandler)
    }
}