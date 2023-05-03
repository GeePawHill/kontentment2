package org.geepawhill.kontentment.app

import javafx.geometry.Orientation
import javafx.scene.Parent
import tornadofx.*

class MainView(val model: Model) : Fragment() {
    override val root: Parent = borderpane {
        center = splitpane {
            orientation = Orientation.HORIZONTAL
            this += ScriptView(model)
            this += CanvasAndDetailView(model)
        }
    }
}