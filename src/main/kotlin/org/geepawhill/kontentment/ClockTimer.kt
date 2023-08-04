package org.geepawhill.kontentment

import javafx.animation.AnimationTimer

class ClockTimer(val handler: (now: Long) -> Unit) : Timer, AnimationTimer() {
    override fun handle(now: Long) {
        handler(now)
    }
}