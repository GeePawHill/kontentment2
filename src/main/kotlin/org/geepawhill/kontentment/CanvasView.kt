package org.geepawhill.kontentment

import javafx.scene.Parent
import org.geepawhill.kontentment.kwrappers.kpresentationpane
import tornadofx.*

class CanvasView(val model: Model) : Fragment() {
    override val root: Parent = borderpane {
        minWidth = 10.0
        minHeight = 10.0
        backgroundProperty().bind(model.windowing.skin.normalBackground)
        top = toolbar {
            backgroundProperty().bind(model.windowing.skin.normalBackground)
            button("Out") {
                model.windowing.isCanvasPopped.addListener { _, old, new ->
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
        center = kpresentationpane(model, 1.7777)
    }
}
