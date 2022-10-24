package org.geepawhill.kontentment

import org.openrndr.draw.Drawer

interface INewPlayer {
    fun play(drawer: Drawer, now: Double): Boolean
}