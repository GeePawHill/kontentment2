package org.geepawhill.kontentment.app

import javafx.scene.Parent
import org.geepawhill.kontentment.app.kwrappers.kborderpane
import org.geepawhill.kontentment.app.kwrappers.klabel
import org.geepawhill.kontentment.app.kwrappers.kstackpane
import org.geepawhill.kontentment.app.kwrappers.ktoolbar
import tornadofx.*

class ScriptView(val model: Model) : Fragment() {
    override val root: Parent = kborderpane(model) {
        left = ktoolbar(model) {
            button("Skin 1") {
                action {
                    model.windowing.skin.select(0)
                }
            }
            button("Skin 2") {
                action {
                    model.windowing.skin.select(1)
                }
            }
        }
        center = kstackpane(model) {
            minWidth = 300.0
            klabel(model, "Script")
        }
    }
}