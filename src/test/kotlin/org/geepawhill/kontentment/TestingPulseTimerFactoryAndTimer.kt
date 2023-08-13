package org.geepawhill.kontentment

class TestingPulseTimerFactoryAndTimer : PulseTimerFactory, PulseTimer {

    var tick: (now: Long) -> Unit = {}
    var startsCalled = 0;
    var stopsCalled = 0;

    override fun makePulseTimer(tick: (now: Long) -> Unit): PulseTimer {
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