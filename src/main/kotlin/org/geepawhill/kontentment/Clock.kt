package org.geepawhill.kontentment

class Clock(timerFactory: TimerFactory, val tick: (milliseconds: Long) -> Unit) {

    private val timer = timerFactory.makeTimer(this::handle)

    private var ms = 0L;
    private var isPlaying = false
    private var seekScheduled = false
    private var pauseScheduled = false
    private var playScheduled = false
    private var lastPulse = 0L

    fun seek(newMs: Long) {
        ms = newMs
        seekScheduled = true
    }

    fun pause() {
        if (!isPlaying) return
        pauseScheduled = true
    }

    fun play() {
        if (isPlaying) return
        playScheduled = true
        timer.start()
    }

    fun handle(now: Long) {
        if (seekScheduled) {
            seekScheduled = false
            lastPulse = now
        }
        if (playScheduled) {
            playScheduled = false
            lastPulse = now
            isPlaying = true
        }
        if (pauseScheduled) {
            timer.stop()
            pauseScheduled = false
            isPlaying = false
        }
        if (isPlaying) {
            val delta = now - lastPulse
            ms += delta / NANOS_TO_MILLIS
            lastPulse = now
            tick(ms)
        }
    }


    companion object {
        const val NANOS_TO_MILLIS = 1000000L
    }
}