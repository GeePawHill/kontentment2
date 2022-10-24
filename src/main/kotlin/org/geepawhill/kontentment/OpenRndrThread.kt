package org.geepawhill.kontentment

import org.openrndr.application
import org.openrndr.math.IntVector2
import kotlin.concurrent.thread

class OpenRndrThread {
    private var played = mutableListOf<Atom>()
    private var sequence = mutableListOf<Atom>()
    var player: OldPlayer
    var playing = false
    var iterator = sequence.iterator()

    private var next = 0

    init {
        (0..5).forEach {
            sequence += LineAtom()
        }
        iterator = sequence.iterator()
        player = OldPlayer(0.0, sequence[next])
        next += 1
    }

    val thread = thread(start = false, isDaemon = true) {
        application {
            configure {
                width = 1080
                height = 720
                position = IntVector2(0, 0)
            }

            program {
                extend {
                    for (atom in played) atom.interpolate(drawer, 1.0)
                    if (playing) {
                        val finished = player.play(drawer, seconds)
                        if (finished) {
                            played += player.atom
                            if (iterator.hasNext()) {
                                player = OldPlayer(seconds, iterator.next())
                            } else playing = false
                        }
                    }
                }
            }
        }
    }

    fun start() {
        thread.start()
    }

    fun play() {
        playing = true
    }
}