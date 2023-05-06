package org.geepawhill.kontentment.app.kwrappers

import javafx.event.EventTarget
import javafx.scene.layout.StackPane
import org.geepawhill.kontentment.app.Model
import tornadofx.*

class KStackPane(val model: Model) : StackPane()

fun EventTarget.kstackpane(
    model: Model,
    op: KStackPane.() -> Unit = {}
): KStackPane {
    val pane = KStackPane(model).apply {
        model.windowing.normalRegion(this)
    }
    return opcr(this, pane, op)
}