package org.geepawhill.kontentment

import javafx.scene.Parent
import org.geepawhill.kontentment.kwrappers.klabel
import org.geepawhill.kontentment.kwrappers.kstackpane
import tornadofx.*

class DetailView(val model: Model) : Fragment() {
    override val root: Parent = kstackpane(model) {
        minHeight = 300.0
        klabel(model, "Detail")
    }
}

