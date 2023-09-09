package org.geepawhill.kontentment

import javafx.geometry.Orientation
import javafx.scene.Parent
import org.geepawhill.kontentment.core.NoStep
import org.geepawhill.kontentment.core.Strokes
import org.geepawhill.kontentment.core.Typing
import tornadofx.*

class MainView(val model: Model) : Fragment() {

    val clockLabel = label(model.gametime) {
        model.windowing.normalLabel(this)
    }
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
            button("NoStep") {
                action {
                    model.currentStep.set(NoStep())
                }
            }
            button("Strokes") {
                action {
                    model.currentStep.set(Strokes())
                }
            }
            button("Typing 1") {
                action {
                    model.currentStep.set(Typing("Typing 1"))
                }
            }
            button("Typing 2") {
                action {
                    model.currentStep.set(Typing("Typing 2"))
                }
            }
        }
        center = splitpane {
            orientation = Orientation.HORIZONTAL
            this += CanvasAndDetailView(model)
            this += ScriptView(model)
        }
    }
}