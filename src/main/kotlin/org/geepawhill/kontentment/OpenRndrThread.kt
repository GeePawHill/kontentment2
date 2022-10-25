package org.geepawhill.kontentment

import org.geepawhill.kontentment.announce.Announcer
import org.geepawhill.kontentment.controller.NowPaused
import org.geepawhill.kontentment.controller.NowPlaying
import org.openrndr.Program
import org.openrndr.application
import org.openrndr.math.IntVector2
import kotlin.concurrent.thread

class OpenRndrThread(_script: Script, val announcer: Announcer) {
    var script = _script

    private val atomClock = AtomClock()
    private var current = script.next()

    val thread = thread(start = false, isDaemon = true) {
        application {
            configure {
                width = 1080
                height = 720
                position = IntVector2(0, 0)
            }

            program {
                extend {
                    tick()
                    drawCompleted()
                    drawCurrent()
                }
            }
        }
    }

    private fun Program.tick() {
        atomClock.tick(seconds)
    }

    private fun Program.drawCurrent() {
        val finished = current.interpolate(drawer, atomClock.delta)
        if (finished) {
            script.finished(current)
            if (script.hasNext()) {
                current = script.next()
                atomClock.reset()
            } else {
                current = Atom.NONE
                pause()
            }
        }
    }

    private fun Program.drawCompleted() {
        script.completed.forEach {
            it.interpolate(drawer)
        }
    }

    fun start() {
        thread.start()
    }

    fun play() {
        atomClock.resume()
        announcer.announce(NowPlaying())
    }

    fun pause() {
        atomClock.pause()
        announcer.announce(NowPaused())
    }
}