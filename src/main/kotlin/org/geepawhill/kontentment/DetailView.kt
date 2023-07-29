package org.geepawhill.kontentment

import javafx.scene.Parent
import javafx.scene.layout.Region
import org.geepawhill.kontentment.kwrappers.klabel
import org.geepawhill.kontentment.kwrappers.kstackpane
import tornadofx.*


fun Region.normalBackground(model: Model) {
    backgroundProperty().bind(model.windowing.skin.normalBackground)
}

class DetailView(val model: Model) : Fragment() {
    override val root: Parent = kstackpane(model) {
        minHeight = 300.0
        klabel(model, "Detail")
    }
}
