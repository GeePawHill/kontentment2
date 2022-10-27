package org.geepawhill.kontentment

import org.geepawhill.kontentment.announce.Announcer
import org.geepawhill.kontentment.render.Renderer

class Script(val announcer: Announcer) {

    val clock = AtomClock(announcer)
    val sequence = mutableListOf<Gesture>()
    val completed = mutableListOf<Gesture>()
    var next = 0
    var current: Gesture

    init {
        sequence += Gesture.NONE
        (1..3).forEach {
            sequence += LineGesture()
        }
        sequence += PauseGesture()
        (1..3).forEach {
            sequence += LineGesture()
        }
        sequence += Gesture.NONE
        current = Gesture.FILL
    }

    fun tick(now: Double, renderer: Renderer) {
        clock.tick(now)
        drawCompleted(renderer)
        drawCurrent(renderer)
    }

    fun drawCompleted(renderer: Renderer) {
        completed.forEach { it.fast(renderer, clock) }
    }

    fun drawCurrent(renderer: Renderer) {
        val finished = current.slow(renderer, clock)
        if (finished) {
            finished()
            current = next()
            clock.reset()
        }
    }

    fun hasNext() = next < sequence.size - 1

    fun next(): Gesture {
        if (!hasNext()) {
            current = Gesture.FILL
        } else {
            current = sequence[next++]
        }
        return current
    }

    fun finished() {
        if (current == Gesture.FILL) return
        completed.add(current)
    }

    fun resume() {
        clock.resume()
    }

    fun pause() {
        clock.pause()
    }
}