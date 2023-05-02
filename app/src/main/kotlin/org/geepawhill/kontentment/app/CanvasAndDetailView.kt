package org.geepawhill.kontentment.app

import javafx.geometry.Orientation
import javafx.scene.Parent
import tornadofx.*

class CanvasAndDetailView : Fragment() {
    override val root: Parent = splitpane {
        orientation = Orientation.VERTICAL
        this += CanvasView()
        this += DetailView()
    }

}
