package org.geepawhill.kontentment

import javafx.scene.canvas.Canvas
import javafx.scene.paint.Color
import tornadofx.*

class JavaFxDrawer(canvas: Canvas) : Drawer {
    val context = canvas.graphicsContext2D
    val width by canvas.widthProperty()
    val height by canvas.heightProperty()

    override fun text(text: String) {
        context.stroke = Color.WHITE
        context.strokeText(text, 0.0, 0.0)
    }

    override fun blank() {
        context.fill = Color.BLACK
        context.fillRect(0.0, 0.0, width, height)
    }
}