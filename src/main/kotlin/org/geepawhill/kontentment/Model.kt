package org.geepawhill.kontentment

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty

class Model {

    val isPlaying = SimpleBooleanProperty(false)
    var playTime = 0.0
    val gametime = SimpleStringProperty("00000000.0")
    val windowing = WindowingModel()
    val presentationWidthToHeight = SimpleDoubleProperty(DEFAULT_WIDTH_TO_HEIGHT)

    private val clock = GameLoop { delta -> tick(delta) }

    fun playOrPause() {
        if (isPlaying.value) pause()
        else play()
        isPlaying.value = !isPlaying.value
    }

    fun tick(delta: Double) {
        playTime += delta
        val text = String.format("%010.1f", playTime)
        gametime.set(text)
    }

    fun play() {
        clock.start()
    }

    fun pause() {
        clock.pause()
    }

    companion object {
        const val DEFAULT_WIDTH_TO_HEIGHT = 1.7777
    }
}