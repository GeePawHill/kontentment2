package org.geepawhill.kontentment.app

import javafx.event.EventTarget
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.layout.Region
import javafx.scene.layout.StackPane
import tornadofx.*


fun Region.normalBackground(model: Model) {
    backgroundProperty().bind(model.windowing.skin.normalBackground)
}

class DetailView(val model: Model) : Fragment() {
    override val root: Parent = kstackpane(model) {
        minHeight = 300.0
        label("Detail")
    }
}

class KStackPane(val model: Model) : StackPane()

fun EventTarget.kstackpane(
    model: Model,
    initialChildren: Iterable<Node>? = null,
    op: KStackPane.() -> Unit = {}
): KStackPane {
    val pane = KStackPane(model).apply {
        if (initialChildren != null) children.addAll(initialChildren)
        normalBackground(model)
    }
    return opcr(this, pane, op)
}