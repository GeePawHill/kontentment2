package org.geepawhill.kontentment.render

import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.math.Vector2

class DrawerRenderer(private val drawer: Drawer) : Renderer {
    override var stroke: ColorRGBa?
        get() = drawer.stroke
        set(value) {
            drawer.stroke = value
        }

    override fun lineSegment(start: Vector2, end: Vector2) {
        drawer.lineSegment(start, end)
    }
}