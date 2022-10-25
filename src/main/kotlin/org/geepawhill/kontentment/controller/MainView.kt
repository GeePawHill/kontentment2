package org.geepawhill.kontentment.controller

import tornadofx.*

class MainView : View("Gerrymandering Game") {
    val model = MainModel()
    override val root = borderpane {
        minWidth = 400.0
        top = toolbar {
            button("Play") {
                enableWhen {
                    model.canPlay
                }
                action {
                    model.play()
                }
            }
            button("Pause") {
                enableWhen {
                    model.canPause
                }
                action {
                    model.pause()
                }
            }
        }
    }

    init {
        currentStage!!.x = 1920.0 - 500.0
        currentStage!!.y = 0.0
    }
}