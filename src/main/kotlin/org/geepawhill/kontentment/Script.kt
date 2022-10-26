package org.geepawhill.kontentment

import org.geepawhill.kontentment.announce.Announcer
import org.geepawhill.kontentment.controller.NowPaused
import org.geepawhill.kontentment.controller.NowPlaying
import org.geepawhill.kontentment.render.Renderer

class Script(val announcer: Announcer) {

    val clock = AtomClock()
    val sequence = mutableListOf<Atom>()
    val completed = mutableListOf<Atom>()
    var next = 0
    var current: Atom

    init {
        sequence += Atom.NONE
        (1..3).forEach {
            sequence += LineAtom()
        }
        sequence += Atom.NONE
        current = Atom.FILL
    }

    fun tick(now: Double, renderer: Renderer) {
        clock.tick(now)
        drawCompleted(renderer)
        drawCurrent(renderer)
    }

    fun drawCompleted(renderer: Renderer) {
        completed.forEach { it.interpolate(renderer, Double.MAX_VALUE) }
    }

    fun drawCurrent(renderer: Renderer) {
        val finished = current.interpolate(renderer, clock.delta)
        if (finished) {
            finished()
            current = next()
            clock.reset()
        }
    }

    fun hasNext() = next < sequence.size - 1

    fun next(): Atom {
        if (!hasNext()) {
            current = Atom.FILL
        } else {
            current = sequence[next++]
        }
        return current
    }

    fun finished() {
        if (current == Atom.FILL) return
        completed.add(current)
        println("Finished")
        completed.forEach { println(it) }
        println("completed")
    }

    fun resume() {
        clock.resume()
        announcer.announce(NowPlaying())
    }

    fun pause() {
        clock.pause()
        announcer.announce(NowPaused())
    }
}