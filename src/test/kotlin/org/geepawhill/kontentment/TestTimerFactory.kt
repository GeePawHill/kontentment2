package org.geepawhill.kontentment

class TestTimerFactory : TimerFactory, Timer {

    var tick: (now: Long) -> Unit = {}
    var startsCalled = 0;
    var stopsCalled = 0;

    override fun makeTimer(tick: (now: Long) -> Unit): Timer {
        this.tick = tick
        return this
    }

    override fun start() {
        startsCalled += 1
    }

    override fun stop() {
        stopsCalled += 1
    }
}