package org.geepawhill.kontentment

import org.geepawhill.kontentment.render.Renderer

interface Gesture {

    fun fast(drawer: Renderer, clock: AtomClock): Boolean
    fun slow(drawer: Renderer, clock: AtomClock): Boolean

    companion object {
        val NONE = object : Gesture {
            override fun fast(drawer: Renderer, clock: AtomClock): Boolean = true
            override fun slow(drawer: Renderer, clock: AtomClock): Boolean = true
        }

        val FILL = object : Gesture {
            override fun fast(drawer: Renderer, clock: AtomClock): Boolean = true
            override fun slow(drawer: Renderer, clock: AtomClock): Boolean = true
        }
    }
}