package org.geepawhill.kontentment

import org.openrndr.draw.Drawer
import java.lang.Double.min

class Player(val atom: Atom) {

    fun play(drawer: Drawer, delta: Double): Boolean {
        val fraction = min(1.0, delta / 2.0)
        atom.interpolate(drawer, fraction)
        return fraction >= 1.0
    }

    fun fraction(delta: Double): Double {
        if (atom.duration == 0.0) return 1.0
        else return min(1.0, delta / atom.duration)
    }
}