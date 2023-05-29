package org.geepawhill.kontentment.app

import javafx.scene.Parent
import org.geepawhill.kontentment.app.kwrappers.KAspectPane
import tornadofx.*

class CanvasView(val model: Model) : Fragment() {
    val aspectPane = KAspectPane(1.777)
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
        center = aspectPane
    }
}
