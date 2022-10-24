package org.geepawhill.kontentment

import org.openrndr.draw.Drawer

class NewPlayer(val start: Double, val atom: Atom, val played: Iterator<Atom>) : INewPlayer {
    override fun play(drawer: Drawer, now: Double): Boolean {
        played.forEachRemaining {
            it.interpolate(drawer, 1.0)
        }
        val delta = now - start
        val fraction = java.lang.Double.min(1.0, delta / 2.0)
        atom.interpolate(drawer, fraction)
        return fraction >= 1.0
    }
}