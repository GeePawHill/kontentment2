package org.geepawhill.kontentment

import org.openrndr.draw.Drawer

interface Atom {
    val duration: Double
    fun interpolate(drawer: Drawer, fraction: Double)

    companion object {
        val NONE = object : Atom {
            override val duration = 0.0
            override fun interpolate(drawer: Drawer, fraction: Double) {
            }
        }
    }
}