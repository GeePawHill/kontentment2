package org.geepawhill.kontentment.kwrappers

import javafx.event.EventTarget
import javafx.scene.canvas.Canvas
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import org.geepawhill.kontentment.Model
import tornadofx.*


class KPresentationPane(val model: Model) : StackPane() {
    val canvas = Canvas(1.777 * 300.0, 300.0)
    private val widthToHeight by model.presentationWidthToHeight

    init {
        model.presentationWidthToHeight.addListener { _, _, _ ->
            resetCanvasDimensions()
        }
        prefWidth = 500.0
        minHeight = 10.0
        minWidth = 10.0
        this += canvas
        drawOnCanvas()
    }

    override fun layoutChildren() {
        println("P: ${width} X ${height}")
        resetCanvasDimensions()
        super.layoutChildren()
    }

    private fun resetCanvasDimensions() {
        val newWidth = snapSizeX(width) - (snappedLeftInset() + snappedRightInset())
        val newHeight = snapSizeY(height) - (snappedTopInset() + snappedBottomInset())

        val widthByHeight = snapSizeY(newWidth / widthToHeight)
        if (widthByHeight < newHeight) {
            canvas.width = newWidth
            canvas.height = widthByHeight
        } else {
            val widthFromHeight = snapSizeX(newHeight * widthToHeight)
            canvas.width = widthFromHeight
            canvas.height = newHeight
        }
        drawOnCanvas()
    }

    fun drawOnCanvas() {
        with(canvas.graphicsContext2D) {
            fill = Color.BLACK
            fillRect(0.0, 0.0, canvas.width, canvas.height)
            stroke = Color.RED
            lineWidth = 1.0
            val endX = canvas.width - 2.0
            val endY = canvas.height - 2.0
            strokeLine(2.0, 2.0, endX, 2.0)
            strokeLine(endX, 2.0, endX, endY)
            strokeLine(endX, endY, 2.0, endY)
            strokeLine(2.0, endY, 2.0, 2.0)
            strokeLine(2.0, 2.0, endX, endY)
            strokeLine(2.0, endY, endX, 2.0)
        }
    }
}

fun EventTarget.kpresentationpane(
    model: Model,
    widthToHeight: Double,
    op: KPresentationPane.() -> Unit = {}
): KPresentationPane {
    val pane = KPresentationPane(model).apply {
        model.windowing.normalRegion(this)
    }
    return opcr(this, pane, op)
}