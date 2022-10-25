package org.geepawhill.kontentment

import org.openrndr.draw.Drawer

class Script {

    val sequence = mutableListOf<Atom>()
    val played = mutableListOf<Atom>()
    var next = 0
    var current: Atom

    init {
        sequence.add(Atom.NONE)
        (0..5).forEach {
            sequence += LineAtom()
        }
        sequence.add(Atom.NONE)
        current = sequence[0]
    }

    fun hasNext() = next < sequence.size - 1

    fun next(start: Double): Atom {
        current = sequence[next++]
        return current
    }

    fun played(drawer: Drawer) {
        played.forEach {
            it.interpolate(drawer, Double.MAX_VALUE)
        }
    }

    fun finished(atom: Atom) {
        played.add(atom)
    }

}