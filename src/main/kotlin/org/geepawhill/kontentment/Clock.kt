package org.geepawhill.kontentment

class Clock(timerFactory: TimerFactory, val tick: (tenths: Long) -> Unit) {

    val timer = timerFactory.makeTimer(this::handle)

    private var isPlaying = false
    private var playScheduled = false
    private var ms = 0L;
    private var lastPulse = 0L

    fun pause() {
    }

    fun play() {
        if (isPlaying) return
        playScheduled = true
        timer.start()
    }

    fun handle(now: Long) {
        if (playScheduled) {
            playScheduled = false
            lastPulse = now
            isPlaying = true
        }
        if (isPlaying) {
            val delta = now - lastPulse
            ms += delta / 1000000L
            lastPulse = now
            tick(ms)
        }
    }

    companion object {
        const val NANOS_TO_DECIS = 100000000L
    }
}