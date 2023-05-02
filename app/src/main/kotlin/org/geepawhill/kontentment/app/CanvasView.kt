package org.geepawhill.kontentment.app

import javafx.scene.Parent
import tornadofx.*

class CanvasView() : Fragment() {
    override val root: Parent = borderpane {
        top = toolbar {
            button("Out") {
                action {
                    
                }
            }
        }
        center = stackpane {
            label("Canvas")
        }
    }
}
