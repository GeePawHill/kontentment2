package org.geepawhill.kontentment.kwrappers

import javafx.event.EventTarget
import javafx.scene.Node
import javafx.scene.control.Label
import org.geepawhill.kontentment.Model
import tornadofx.*

class KLabel(val model: Model, text: String) : Label(text)

fun EventTarget.klabel(model: Model, text: String = "", graphic: Node? = null, op: Label.() -> Unit = {}): KLabel {
    return KLabel(model, text).attachTo(this, op) {
        if (graphic != null) it.graphic = graphic
        model.windowing.normalLabel(it)
    }
}
