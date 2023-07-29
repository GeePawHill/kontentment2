package org.geepawhill.kontentment

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application() {
    override fun start(primaryStage: Stage?) {
        val model = Model()
        val view = MainView(model).root
        primaryStage!!.scene = Scene(view)
        primaryStage.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args)
}