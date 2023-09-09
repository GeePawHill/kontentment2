package org.geepawhill.kontentment.kwrappers

import javafx.beans.property.StringProperty
import javafx.event.EventTarget
import javafx.scene.Node
import javafx.scene.control.Label
import org.geepawhill.kontentment.Model
import tornadofx.*

class KLabel() : Label()

fun EventTarget.klabel(model: Model, text: String = "", graphic: Node? = null, op: Label.() -> Unit = {}): KLabel {
    val new = KLabel().apply {
        this.text = text
    }
    return new.attachTo(this, op) {
        if (graphic != null) it.graphic = graphic
        model.windowing.normalLabel(it)
    }
}

fun EventTarget.klabel(
    model: Model,
    text: StringProperty,
    graphic: Node? = null,
    op: Label.() -> Unit = {}
): KLabel {
    val new = KLabel().apply {
        this.textProperty().bind(text)
    }
    return new.attachTo(this, op) {
        if (graphic != null) it.graphic = graphic
        model.windowing.normalLabel(it)
    }
}
