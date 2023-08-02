package org.geepawhill.kontentment

import javafx.geometry.Orientation
import javafx.scene.Parent
import tornadofx.*

class MainView(val model: Model) : Fragment() {

    val clockLabel = label(model.gametime)
    val playButton = button("Play") {
        action {
            model.playOrPause()
        }
        model.isPlaying.addListener { _, _, _ ->
            if (model.isPlaying.value == true) text = "Pause"
            else text = "Play"
        }
    }

    override val root: Parent = borderpane {
        top = toolbar {
            this += clockLabel
            this += playButton
        }
        center = splitpane {
            orientation = Orientation.HORIZONTAL
            this += CanvasAndDetailView(model)
            this += ScriptView(model)
        }
    }
}