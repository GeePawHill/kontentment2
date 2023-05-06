package org.geepawhill.kontentment.app

import javafx.scene.Parent
import org.geepawhill.kontentment.app.kwrappers.klabel
import org.geepawhill.kontentment.app.kwrappers.kstackpane
import tornadofx.*

class CanvasView(val model: Model) : Fragment() {
    override val root: Parent = borderpane {
        minWidth = 400.0
        minHeight = 300.0
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
        center = kstackpane(model) {
            klabel(model, "Canvas")
        }
    }
}
