package org.geepawhill.kontentment

import javafx.scene.Parent
import org.geepawhill.kontentment.kwrappers.kborderpane
import org.geepawhill.kontentment.kwrappers.klabel
import org.geepawhill.kontentment.kwrappers.kstackpane
import org.geepawhill.kontentment.kwrappers.ktoolbar
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
            button("display") {
                action {
                    model.presentationWidthToHeight.value = 1.7777
                }
            }
            button("square") {
                action {
                    model.presentationWidthToHeight.value = 1.0
                }
            }
        }
        center = kstackpane(model) {
            minWidth = 300.0
            klabel(model, "Script")
        }
    }
}