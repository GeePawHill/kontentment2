package org.geepawhill.kontentment

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty

class Model {
    fun playOrPause() {
        isPlaying.value = !isPlaying.value
    }

    val isPlaying = SimpleBooleanProperty(false)
    val gametime = SimpleStringProperty("0::000")
    val windowing = WindowingModel()
    val presentationWidthToHeight = SimpleDoubleProperty(DEFAULT_WIDTH_TO_HEIGHT)

    companion object {
        const val DEFAULT_WIDTH_TO_HEIGHT = 1.7777
    }
}