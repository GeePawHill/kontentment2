package org.geepawhill.kontentment

class TestingPulseTimerFactoryAndTimer : PulseTimerFactory, PulseTimer {

    var tick: (now: Long) -> Unit = {}
    var startsCalled = 0;
    var stopsCalled = 0;

    override fun makePulseTimer(pulseHandler: (ns: Long) -> Unit): PulseTimer {
        this.tick = pulseHandler
        return this
    }

    override fun start() {
        startsCalled += 1
    }

    override fun stop() {
        stopsCalled += 1
    }
}