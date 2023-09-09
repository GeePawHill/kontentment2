package org.geepawhill.kontentment

import javafx.scene.Parent
import org.geepawhill.kontentment.kwrappers.klabel
import tornadofx.*

class StrokesDetailView(model: Model) : Fragment() {
    override val root = vbox {
        klabel(model,"Strokes Detail")
    }
}
