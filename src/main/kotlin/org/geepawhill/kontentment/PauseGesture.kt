package org.geepawhill.kontentment

import org.geepawhill.kontentment.render.Renderer

class PauseGesture : Gesture {
    override fun fast(drawer: Renderer, clock: AtomClock): Boolean = true

    override fun slow(drawer: Renderer, clock: AtomClock): Boolean {
        clock.pause()
        return true
    }
}