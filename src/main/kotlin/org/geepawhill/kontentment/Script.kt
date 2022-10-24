package org.geepawhill.kontentment

import org.openrndr.draw.Drawer

class Script {

    val sequence = mutableListOf<Atom>()
    val played = mutableListOf<Atom>()
    var next = 0
    var current: Player

    init {
        sequence.add(Atom.NONE)
        (0..5).forEach {
            sequence += LineAtom()
        }
        current = Player(0.0, sequence[0], emptyList())
    }

    fun hasNext() = next < sequence.size - 1

    fun next(start: Double): Player {
        current = Player(start, sequence[next++], played)
        return current
    }

    fun played(drawer: Drawer) {
        played.forEach {
            it.interpolate(drawer, 1.0)
        }
    }

    fun finished(player: Player) {
        played.add(player.atom)
    }

}