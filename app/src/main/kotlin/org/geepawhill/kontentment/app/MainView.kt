package org.geepawhill.kontentment.app

import javafx.geometry.Orientation
import javafx.scene.Parent
import tornadofx.*

class MainView : Fragment() {
    override val root: Parent = borderpane {
        center = splitpane {
            orientation = Orientation.HORIZONTAL
            this += ScriptView()
            this += CanvasAndDetailView()
        }
    }

}