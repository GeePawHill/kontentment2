package org.geepawhill.kontentment.kwrappers

import javafx.event.EventTarget
import javafx.scene.layout.BorderPane
import org.geepawhill.kontentment.Model
import tornadofx.*

class KBorderPane(val model: Model) : BorderPane()

fun EventTarget.kborderpane(
    model: Model,
    op: KBorderPane.() -> Unit = {}
): KBorderPane {
    val pane = KBorderPane(model).apply {
        model.windowing.normalRegion(this)
    }
    return opcr(this, pane, op)
}