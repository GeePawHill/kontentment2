package org.geepawhill.kontentment

import org.openrndr.draw.Drawer

class OldPlayer(val start: Double, val atom: Atom) {

    fun play(drawer: Drawer, now: Double): Boolean {
        val delta = now - start
        val fraction = java.lang.Double.min(1.0, delta / 2.0)
        atom.interpolate(drawer, fraction)
        return fraction >= 1.0
    }
}