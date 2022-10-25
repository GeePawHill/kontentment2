package org.geepawhill.kontentment

import org.geepawhill.kontentment.announce.Announcer
import org.geepawhill.kontentment.controller.NowPaused
import org.geepawhill.kontentment.controller.NowPlaying
import org.openrndr.application
import org.openrndr.math.IntVector2
import kotlin.concurrent.thread

class OpenRndrThread(_script: Script, val announcer: Announcer) {
    var script = _script
    var playing = false

    private var next = 0

    private val atomClock = AtomClock()
    private var atom = script.next(0.0)

    val thread = thread(start = false, isDaemon = true) {
        application {
            configure {
                width = 1080
                height = 720
                position = IntVector2(0, 0)
            }

            program {
                extend {
                    atomClock.tick(seconds)
                    script.played(drawer)
                    val finished = atom.interpolate(drawer, atomClock.delta)
                    if (finished) {
                        script.finished(atom)
                        if (script.hasNext()) {
                            atom = script.next(0.0)
                            atomClock.reset()
                        } else {
                            atom = Atom.NONE
                            pause()
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
        atomClock.resume()
        announcer.announce(NowPlaying())
    }

    fun pause() {
        playing = false
        atomClock.pause()
        announcer.announce(NowPaused())
    }
}