package org.geepawhill.kontentment

class Clock {

    var delta: Double = 0.0
        private set

    private var real = 0.0
    private var start = 0.0
    private var playing = false

    fun reset() {
        start = real
        delta = 0.0
    }

    fun resume() {
        if (playing) return
        playing = true
        start = real - delta
    }

    fun pause() {
        if (!playing) return
        playing = false
        start = real - delta
    }

    fun tick(now: Double) {
        real = now
        if (playing) {
            delta = now - start
        }
    }
}