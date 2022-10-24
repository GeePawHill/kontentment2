package org.geepawhill.kontentment

import org.openrndr.draw.Drawer

interface Atom {
    fun interpolate(drawer: Drawer, fraction: Double)
}