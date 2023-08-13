package org.geepawhill.kontentment

import javafx.animation.AnimationTimer

class JavaFxPulseTimer(val handler: (ns: Long) -> Unit) : PulseTimer, AnimationTimer() {
    override fun handle(ns: Long) {
        handler(ns)
    }
}