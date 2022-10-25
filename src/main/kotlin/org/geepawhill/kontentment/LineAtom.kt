package org.geepawhill.kontentment

import org.openrndr.color.ColorRGBa
import org.openrndr.draw.Drawer
import org.openrndr.extra.noise.Random

class LineAtom : Atom {
    val width = 1080.0
    val height = 720.0

    val from = Point(Random.double(0.0, width), Random.double(0.0, height))
    val to = Point(Random.double(0.0, width), Random.double(0.0, height))

    override val duration = from.distance(to) * 0.2

    override fun interpolate(drawer: Drawer, fraction: Double) {
        val newTo = from.along(fraction, to)
        drawer.stroke = ColorRGBa.WHITE
        drawer.lineSegment(from.toVector(), newTo.toVector())
    }
}