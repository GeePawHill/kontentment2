package org.geepawhill.kontentment

import org.openrndr.draw.Drawer
import java.lang.Double.min

class Player(val start: Double, val atom: Atom, val played: List<Atom>) {

    fun play(drawer: Drawer, now: Double): Boolean {
        val delta = now - start
        val fraction = min(1.0, delta / 2.0)
        atom.interpolate(drawer, fraction)
        return fraction >= 1.0
    }
}