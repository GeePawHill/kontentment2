package org.geepawhill.kontentment

class Clock(timerFactory: TimerFactory, val tick: (tenths: Long) -> Unit) {

    val timer = timerFactory.makeTimer(this::handle)

    var pauseStart: Long = 0
    var animationStart: Long = 0
    var lastFrameTimeNanos: Long = 0
    var isPaused = false
    var isActive = false
    var pauseScheduled = false
    var playScheduled = false
    var restartScheduled = false

    fun pause() {
        if (!isPaused) {
            pauseScheduled = true
        }
    }

    fun play() {
        if (isPaused) {
            playScheduled = true
        }
    }

    fun start() {
        timer.start()
        isActive = true
        restartScheduled = true
    }

    fun stop() {
        timer.stop()
        pauseStart = 0
        isPaused = false
        isActive = false
        pauseScheduled = false
        playScheduled = false
    }

    fun handle(now: Long) {
        if (pauseScheduled) {
            pauseStart = now
            isPaused = true
            pauseScheduled = false
        }
        if (playScheduled) {
            animationStart += now - pauseStart
            isPaused = false
            playScheduled = false
        }
        if (restartScheduled) {
            isPaused = false
            animationStart = now
            restartScheduled = false
        }
        if (!isPaused) {
            val decisSinceLastFrame = ((now - lastFrameTimeNanos) / NANOS_TO_DECIS)
            lastFrameTimeNanos = now
            tick(decisSinceLastFrame)
        }
    }

    companion object {
        const val NANOS_TO_DECIS = 100000000L
    }
}