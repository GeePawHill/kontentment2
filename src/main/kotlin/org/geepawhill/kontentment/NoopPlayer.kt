package org.geepawhill.kontentment

import org.openrndr.draw.Drawer

class NoopPlayer() : INewPlayer {
    override fun play(drawer: Drawer, now: Double): Boolean {
        return true
    }
}