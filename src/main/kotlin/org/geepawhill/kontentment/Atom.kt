package org.geepawhill.kontentment

import org.openrndr.draw.Drawer

interface Atom {
    fun interpolate(drawer: Drawer, delta: Double): Boolean

    companion object {
        val NONE = object : Atom {
            override fun interpolate(drawer: Drawer, delta: Double): Boolean = true
        }
    }
}