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
    var player: Player = script.next(0.0)

    private var next = 0

    val thread = thread(start = false, isDaemon = true) {
        application {
            configure {
                width = 1080
                height = 720
                position = IntVector2(0, 0)
            }

            program {
                extend {
                    script.played(drawer)
                    if (playing) {
                        val finished = player.play(drawer, seconds)
                        if (finished) {
                            script.finished(player)
                            if (script.hasNext()) player = script.next(seconds)
                            else pause()
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
        announcer.announce(NowPlaying())
    }

    fun pause() {
        playing = false
        announcer.announce(NowPaused())
    }
}