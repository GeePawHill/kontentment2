package org.geepawhill.kontentment.app

import javafx.scene.Parent
import tornadofx.*

class CanvasView(val model: Model) : Fragment() {
    override val root: Parent = borderpane {
        top = toolbar {
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
        center = stackpane {
            label("Canvas")
        }
    }
}
