package org.geepawhill.kontentment

import org.openrndr.application
import org.openrndr.math.IntVector2
import kotlin.concurrent.thread

class OpenRndrThread(_script: Script) {
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
                            else playing = false
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