package org.geepawhill.kontentment

import javafx.animation.AnimationTimer
import org.geepawhill.kontentment.core.PulseTimer

class JavaFxPulseTimer(val handler: (ns: Long) -> Unit) : PulseTimer, AnimationTimer() {
    override fun handle(ns: Long) {
        handler(ns)
    }
}