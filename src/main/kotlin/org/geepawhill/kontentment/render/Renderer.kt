package org.geepawhill.kontentment.render

import org.openrndr.color.ColorRGBa
import org.openrndr.math.Vector2

interface Renderer {
    var stroke: ColorRGBa?
    fun lineSegment(start: Vector2, end: Vector2)
}