package org.geepawhill.kontentment

import org.geepawhill.kontentment.announce.Announcer
import org.geepawhill.kontentment.controller.NowPaused
import org.geepawhill.kontentment.controller.NowPlaying

class AtomClock(val announcer: Announcer) {

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
        announcer.announce(NowPlaying())
    }

    fun pause() {
        if (!playing) return
        playing = false
        start = real - delta
        announcer.announce(NowPaused())
    }

    fun tick(now: Double) {
        real = now
        if (playing) {
            delta = now - start
        }
    }

}