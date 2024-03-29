package org.geepawhill.kontentment

import javafx.event.EventHandler
import javafx.geometry.Orientation
import javafx.scene.Scene
import javafx.scene.control.SplitPane
import javafx.stage.Stage
import org.geepawhill.kontentment.kwrappers.kstackpane
import tornadofx.*

class CanvasAndDetailView(val model: Model) : Fragment() {
    private val canvasView = CanvasView(model)
    private val canvasStage = Stage()
    private val canvasFrame = kstackpane(model)

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
        model.windowing.isCanvasPopped.addListener { _, _, _ -> changeCanvas() }
        model.windowing.isCanvasFullScreen.addListener { _, _, _ -> canvasFullScreen() }
    }

    private fun changeCanvas() {
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

    private fun canvasFullScreen() {
    }

}
