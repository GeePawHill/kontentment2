package org.geepawhill.kontentment

import javafx.scene.Parent
import org.geepawhill.kontentment.kwrappers.kpresentationpane
import tornadofx.*

class CanvasView(val model: Model) : Fragment() {
    override val root: Parent = borderpane {
        minWidth = 10.0
        minHeight = 10.0
        model.windowing.normalRegion(this)
        top = toolbar {
            model.windowing.normalToolbar(this)
            button("Out") {
                model.windowing.isCanvasPopped.addListener { _, _, new ->
                    when (new) {
                        true -> text = "In"
                        false -> text = "out"
                    }
                }
                action {
                    model.windowing.isCanvasPopped.value = !model.windowing.isCanvasPopped.value
                }
            }
        }
        center = kpresentationpane(model)
    }
}
