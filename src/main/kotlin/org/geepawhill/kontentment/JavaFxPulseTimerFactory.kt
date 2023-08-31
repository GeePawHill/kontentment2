package org.geepawhill.kontentment

import org.geepawhill.kontentment.core.PulseTimer
import org.geepawhill.kontentment.core.PulseTimerFactory

class JavaFxPulseTimerFactory : PulseTimerFactory {
    override fun makePulseTimer(pulseHandler: (ns: Long) -> Unit): PulseTimer {
        return JavaFxPulseTimer(pulseHandler)
    }
}