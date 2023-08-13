package org.geepawhill.kontentment

class Clock(pulseTimerFactory: PulseTimerFactory, val announceTime: (milliseconds: Long) -> Unit) {

    private val pulseTimer = pulseTimerFactory.makePulseTimer(this::pulse)

    private var milliseconds = 0L;

    private var isPlaying = false

    private var seekScheduled = false
    private var pauseScheduled = false
    private var playScheduled = false

    private var lastPulse = 0L

    fun play() {
        if (isPlaying) return
        playScheduled = true
        pulseTimer.start()
    }

    fun pause() {
        if (!isPlaying) return
        pauseScheduled = true
    }

    fun seek(newMs: Long) {
        milliseconds = newMs
        seekScheduled = true
    }

    fun pulse(now: Long) {
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
            pulseTimer.stop()
            pauseScheduled = false
            isPlaying = false
        }
        if (isPlaying) {
            val delta = now - lastPulse
            milliseconds += delta / NANOS_PER_MILLI
            lastPulse = now
            announceTime(milliseconds)
        }
    }


    companion object {
        const val NANOS_PER_MILLI = 1000000L
    }
}