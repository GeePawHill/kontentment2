package org.geepawhill.kontentment

class Clock(pulseTimerFactory: PulseTimerFactory, val announceTime: (currentAnimationTimeMs: Long) -> Unit) {

    private val pulseTimer = pulseTimerFactory.makePulseTimer(this::pulse)

    private var currentAnimationTimeMs = 0L;

    private var isPlaying = false

    private var forceDeltaToZero = false

    private var lastPulseNs = 0L

    fun play() {
        if (isPlaying) return
        forceDeltaToZero = true
        isPlaying = true
        pulseTimer.start()
    }

    fun pause() {
        if (!isPlaying) return
        pulseTimer.stop()
        isPlaying = false
    }

    fun seek(newAnimationTimeMs: Long) {
        currentAnimationTimeMs = newAnimationTimeMs
        forceDeltaToZero = true
    }

    fun reAnnounceTime() {
        announceTime(currentAnimationTimeMs)
    }

    fun pulse(pulseNs: Long) {
        if (forceDeltaToZero) {
            forceDeltaToZero = false
            lastPulseNs = pulseNs
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