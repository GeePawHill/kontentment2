package org.geepawhill.kontentment

class Clock(pulseTimerFactory: PulseTimerFactory, val announceTime: (currentAnimationTimeMs: Long) -> Unit) {

    private val pulseTimer = pulseTimerFactory.makePulseTimer(this::pulse)

    private var currentAnimationTimeMs = 0L;

    private var isPlaying = false

    private var seekScheduled = false
    private var pauseScheduled = false
    private var playScheduled = false

    private var lastPulseNs = 0L

    fun play() {
        if (isPlaying) return
        playScheduled = true
        pulseTimer.start()
    }

    fun pause() {
        if (!isPlaying) return
        pauseScheduled = true
    }

    fun seek(newAnimationTimeMs: Long) {
        currentAnimationTimeMs = newAnimationTimeMs
        seekScheduled = true
    }

    fun pulse(pulseNs: Long) {
        if (seekScheduled) {
            seekScheduled = false
            lastPulseNs = pulseNs
        }
        if (playScheduled) {
            playScheduled = false
            lastPulseNs = pulseNs
            isPlaying = true
        }
        if (pauseScheduled) {
            pulseTimer.stop()
            pauseScheduled = false
            isPlaying = false
        }
        if (isPlaying) {
            val deltaNs = pulseNs - lastPulseNs
            currentAnimationTimeMs += deltaNs / NANOS_PER_MILLI
            lastPulseNs = pulseNs
            announceTime(currentAnimationTimeMs)
        }
    }

    companion object {
        const val NANOS_PER_MILLI = 1000000L
    }
}