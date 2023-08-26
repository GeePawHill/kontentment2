package org.geepawhill.kontentment.kwrappers

import javafx.event.EventTarget
import javafx.scene.canvas.Canvas
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import org.geepawhill.kontentment.JavaFxDrawer
import org.geepawhill.kontentment.Model
import tornadofx.*

class KPresentationPane(val model: Model) : StackPane() {
    private val canvas = Canvas(DEFAULT_WIDTH, DEFAULT_HEIGHT)

    init {
        model.presentationWidthToHeight.addListener { _, _, _ ->
            resetCanvasDimensions()
        }
        prefWidth = DEFAULT_WIDTH
        minHeight = 10.0
        minWidth = 10.0
        this += canvas
        model.drawer.set(JavaFxDrawer(canvas))
    }

    override fun layoutChildren() {
        resetCanvasDimensions()
        super.layoutChildren()
    }

    private fun resetCanvasDimensions() {
        val heightConstrainedByWidth = snapSizeY(contentWidth() / model.presentationWidthToHeight.value)
        val widthConstraintedByHeight = snapSizeX(contentHeight() * model.presentationWidthToHeight.value)

        if (heightConstrainedByWidth < contentHeight()) {
            canvas.width = contentWidth()
            canvas.height = heightConstrainedByWidth
        } else {
            canvas.width = widthConstraintedByHeight
            canvas.height = contentHeight()
        }
    }


    private fun contentHeight() = snapSizeY(height) - (snappedTopInset() + snappedBottomInset())

    private fun contentWidth() = snapSizeX(width) - (snappedLeftInset() + snappedRightInset())

    private fun drawOnCanvas() {
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

    companion object {
        private const val DEFAULT_HEIGHT = 300.0
        private const val DEFAULT_WIDTH = 1.777 * DEFAULT_HEIGHT
    }
}

fun EventTarget.kpresentationpane(
    model: Model,
    op: KPresentationPane.() -> Unit = {}
): KPresentationPane {
    val pane = KPresentationPane(model).apply {
        model.windowing.normalRegion(this)
    }
    return opcr(this, pane, op)
}