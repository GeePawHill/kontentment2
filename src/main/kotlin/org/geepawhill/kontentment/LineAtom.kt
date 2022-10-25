package org.geepawhill.kontentment

import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.extra.noise.Random
import java.lang.Double.min

class LineAtom : Atom {
    val width = 1080.0
    val height = 720.0

    val from = Point(Random.double(0.0, width), Random.double(0.0, height))
    val to = Point(Random.double(0.0, width), Random.double(0.0, height))

    val duration = from.distance(to) * 0.005

    override fun interpolate(drawer: Drawer, delta: Double): Boolean {
        val fraction = min(1.0, delta / duration)
        val newTo = from.along(fraction, to)
        drawer.stroke = ColorRGBa.WHITE
        drawer.lineSegment(from.toVector(), newTo.toVector())
        return fraction == 1.0
    }
}