package org.geepawhill.kontentment

import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleDoubleProperty

class Clock(timerFactory: JavaFxTimerFactory, val tick: (delta: Double) -> Unit) {

    val pulse = timerFactory.makeTimer(this::handle)

    var pauseStart: Long = 0
    var animationStart: Long = 0
    var animationDuration: DoubleProperty = SimpleDoubleProperty(0.0)
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
        pulse.start()
        isActive = true
        restartScheduled = true
    }

    fun stop() {
        pulse.stop()
        pauseStart = 0
        isPaused = false
        isActive = false
        pauseScheduled = false
        playScheduled = false
        animationDuration.set(0.0)
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
            val animDuration = now - animationStart
            animationDuration.set(animDuration / 1e9)
            val secondsSinceLastFrame = ((now - lastFrameTimeNanos) / 1e9)
            lastFrameTimeNanos = now
            tick(secondsSinceLastFrame)
        }
    }
}