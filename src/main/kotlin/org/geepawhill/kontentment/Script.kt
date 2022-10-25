package org.geepawhill.kontentment

class Script {

    val sequence = mutableListOf<Atom>()
    val completed = mutableListOf<Atom>()
    var next = 0
    var current: Atom

    init {
        sequence.add(Atom.NONE)
        (0..99).forEach {
            sequence += LineAtom()
        }
        sequence.add(Atom.NONE)
        current = sequence[0]
    }

    fun hasNext() = next < sequence.size - 1

    fun next(): Atom {
        current = sequence[next++]
        return current
    }

    fun finished(atom: Atom) {
        completed.add(atom)
    }

}