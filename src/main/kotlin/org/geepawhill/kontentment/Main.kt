package org.geepawhill.kontentment

import org.openrndr.application
import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.extra.noise.Random
import org.openrndr.math.IntVector2
import tornadofx.*
import java.lang.Double.min
import kotlin.concurrent.thread

class MainView : View("Gerrymandering Game") {
    val thread = OpenRndrThread().apply { start() }
    override val root = vbox {
        minWidth = 400.0
        label("Hi Mom!")
        button("Start") {
            action {
                thread.play()
            }
        }
    }

    init {
        currentStage!!.x = 1920.0 - 500.0
        currentStage!!.y = 0.0
    }
}

class Main : App(MainView::class)

interface Atom {
    fun interpolate(drawer: org.openrndr.draw.Drawer, fraction: Double)
}

class LineAtom : Atom {
    val width = 1080.0
    val height = 720.0

    val from = Point(Random.double(0.0, width), Random.double(0.0, height))
    val to = Point(Random.double(0.0, width), Random.double(0.0, height))

    override fun interpolate(drawer: Drawer, fraction: Double) {
        val newTo = from.along(fraction, to)
        drawer.stroke = ColorRGBa.WHITE
        drawer.lineSegment(from.toVector(), newTo.toVector())
    }
}

class Player(val start: Double, val atom: Atom) {

    fun play(drawer: Drawer, now: Double): Boolean {
        val delta = now - start
        val fraction = min(1.0, delta / 2.0)
        atom.interpolate(drawer, fraction)
        return fraction >= 1.0
    }
}

interface INewPlayer {
    fun play(drawer: Drawer, now: Double): Boolean
}

class NewPlayer(val start: Double, val atom: Atom, val played: Iterator<Atom>) : INewPlayer {
    override fun play(drawer: org.openrndr.draw.Drawer, now: Double): Boolean {
        played.forEachRemaining {
            it.interpolate(drawer, 1.0)
        }
        val delta = now - start
        val fraction = min(1.0, delta / 2.0)
        atom.interpolate(drawer, fraction)
        return fraction >= 1.0
    }
}

class NoopPlayer() : INewPlayer {
    override fun play(drawer: Drawer, now: Double): Boolean {
        return true
    }
}

class OpenRndrThread {
    private var played = mutableListOf<Atom>()
    private var sequence = mutableListOf<Atom>()
    var player: Player
    var playing = false
    var iterator = sequence.iterator()

    private var next = 0

    init {
        (0..5).forEach {
            sequence += LineAtom()
        }
        iterator = sequence.iterator()
        player = Player(0.0, sequence[next])
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
                                player = Player(seconds, iterator.next())
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

fun main() {
    launch<Main>()
}
