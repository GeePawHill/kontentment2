package org.geepawhill.kontentment

import org.geepawhill.kontentment.render.Renderer

interface Atom {
    fun interpolate(drawer: Renderer, delta: Double = Double.MAX_VALUE): Boolean

    companion object {
        val NONE = object : Atom {
            override fun interpolate(drawer: Renderer, delta: Double): Boolean = true
        }

        val FILL = object : Atom {
            override fun interpolate(drawer: Renderer, delta: Double): Boolean = true
        }
    }
}