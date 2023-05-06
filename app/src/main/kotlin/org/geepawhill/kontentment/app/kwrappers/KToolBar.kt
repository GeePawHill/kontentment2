package org.geepawhill.kontentment.app.kwrappers

import javafx.event.EventTarget
import javafx.scene.control.ToolBar
import org.geepawhill.kontentment.app.Model
import tornadofx.*

class KToolBar(val model: Model) : ToolBar()

fun EventTarget.ktoolbar(
    model: Model,
    op: KToolBar.() -> Unit = {}
): KToolBar {
    val pane = KToolBar(model).apply {
        model.windowing.normalRegion(this)
    }
    return opcr(this, pane, op)
}