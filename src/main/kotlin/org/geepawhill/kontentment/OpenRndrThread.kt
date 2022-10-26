package org.geepawhill.kontentment

import org.openrndr.Program
import org.openrndr.application
import org.openrndr.math.IntVector2
import kotlin.concurrent.thread

class OpenRndrThread(_script: Script) {
    var script = _script

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
        script.clock.tick(seconds)
    }

    private fun Program.drawCompleted() {
        script.completed.forEach {
            it.interpolate(drawer)
        }
    }

    private fun Program.drawCurrent() {
        val finished = current.interpolate(drawer, script.clock.delta)
        if (finished) {
            script.finished()
            current = script.next()
            script.clock.reset()
        }
    }

    fun start() {
        thread.start()
    }


}