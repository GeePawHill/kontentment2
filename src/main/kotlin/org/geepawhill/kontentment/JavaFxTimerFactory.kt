package org.geepawhill.kontentment

class JavaFxTimerFactory : TimerFactory {
    override fun makeTimer(tick: (now: Long) -> Unit): Timer {
        return JavaFxTimer(tick)
    }
}