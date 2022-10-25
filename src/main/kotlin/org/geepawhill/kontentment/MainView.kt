package org.geepawhill.kontentment

import tornadofx.*

class MainView : View("Gerrymandering Game") {
    val model = MainModel()
    override val root = borderpane {
        minWidth = 400.0
        top = toolbar {
            button("Play") {
                action {
                    model.play()
                }
            }
        }
    }

    init {
        currentStage!!.x = 1920.0 - 500.0
        currentStage!!.y = 0.0
    }
}