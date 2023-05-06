package org.geepawhill.kontentment.app

import javafx.event.EventHandler
import javafx.geometry.Orientation
import javafx.scene.Scene
import javafx.scene.control.SplitPane
import javafx.stage.Stage
import tornadofx.*

class CanvasAndDetailView(val model: Model) : Fragment() {
    val canvasView = CanvasView(model)
    val canvasStage = Stage()
    val canvasFrame = stackpane()

    override val root: SplitPane = splitpane {
        orientation = Orientation.VERTICAL
        this += canvasView
        this += DetailView(model)
    }

    init {
        canvasStage.scene = Scene(canvasFrame)
        canvasStage.onCloseRequest = EventHandler { _ ->
            model.windowing.isCanvasPopped.value = false
        }
        model.windowing.isCanvasPopped.addListener { _, _, new -> changeCanvas() }
    }

    fun changeCanvas() {
        if (model.windowing.isCanvasPopped.value) {
            root.items.remove(canvasView.root)
            canvasFrame.children.add(canvasView.root)
            canvasStage.show()
        } else {
            canvasStage.hide()
            canvasFrame.children.remove(canvasView.root)
            root.items.add(0, canvasView.root)
        }
    }

}
