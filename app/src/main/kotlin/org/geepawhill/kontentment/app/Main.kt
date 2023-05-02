package org.geepawhill.kontentment.app

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application() {
    override fun start(primaryStage: Stage?) {
        val view = MainView().root
        primaryStage!!.scene = Scene(view)
        primaryStage.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args)
}