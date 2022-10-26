package org.geepawhill.kontentment

import org.geepawhill.kontentment.render.DrawerRenderer
import org.openrndr.application
import org.openrndr.math.IntVector2
import kotlin.concurrent.thread

class OpenRndrThread(_script: Script) {
    var script = _script

    val thread = thread(start = false, isDaemon = true) {
        application {
            configure {
                width = 1080
                height = 720
                position = IntVector2(0, 0)
            }

            program {
                val renderer = DrawerRenderer(drawer)
                extend {
                    script.tick(seconds, renderer)
                }
            }
        }
    }

    init {
        thread.start()
    }
}