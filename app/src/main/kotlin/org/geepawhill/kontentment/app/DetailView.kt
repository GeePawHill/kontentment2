package org.geepawhill.kontentment.app

import javafx.scene.Parent
import tornadofx.*

class DetailView(val model: Model) : Fragment() {
    override val root: Parent = stackpane {
        label("Detail")
    }
}
