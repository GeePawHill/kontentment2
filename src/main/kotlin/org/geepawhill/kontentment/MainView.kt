package org.geepawhill.kontentment

import tornadofx.*

class MainView : View("Gerrymandering Game") {
    val script = Script()
    val thread = OpenRndrThread(script).apply { start() }
    override val root = vbox {
        minWidth = 400.0
        label("Hi Mom!")
        button("Start") {
            action {
                thread.play()
            }
        }
    }

    init {
        currentStage!!.x = 1920.0 - 500.0
        currentStage!!.y = 0.0
    }
}