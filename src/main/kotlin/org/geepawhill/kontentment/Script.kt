package org.geepawhill.kontentment

class Script {

    val sequence = mutableListOf<Atom>()
    val completed = mutableListOf<Atom>()
    var next = 0
    var current: Atom

    init {
        sequence += Atom.NONE
        (1..3).forEach {
            sequence += LineAtom()
        }
        sequence += Atom.NONE
        current = sequence[0]
    }

    fun hasNext() = next < sequence.size - 1

    fun next(): Atom {
        if (!hasNext()) {
            current = Atom.FILL
        } else {
            current = sequence[next++]
        }
        return current
    }

    fun finished() {
        if (current == Atom.FILL) return
        completed.add(current)
        println("Finished")
        completed.forEach { println(it) }
        println("completed")
    }

}