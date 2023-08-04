package org.geepawhill.kontentment

class TestTimerFactory : TimerFactory, Timer {

    var tick: (now: Long) -> Unit = {}

    override fun makeTimer(tick: (now: Long) -> Unit): Timer {
        this.tick = tick
        return this
    }

    override fun start() {
    }

    override fun stop() {
    }
}