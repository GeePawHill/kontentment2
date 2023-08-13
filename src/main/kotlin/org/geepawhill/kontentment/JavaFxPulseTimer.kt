package org.geepawhill.kontentment

import javafx.animation.AnimationTimer

class JavaFxPulseTimer(val handler: (now: Long) -> Unit) : PulseTimer, AnimationTimer() {
    override fun handle(now: Long) {
        handler(now)
    }
}