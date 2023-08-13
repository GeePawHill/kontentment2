package org.geepawhill.kontentment

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty

class Model {

    val isPlaying = SimpleBooleanProperty(false)
    var playTime = 0L
    val gametime = SimpleStringProperty("000000000")
    val windowing = WindowingModel()
    val presentationWidthToHeight = SimpleDoubleProperty(DEFAULT_WIDTH_TO_HEIGHT)

    private val clock = Clock(JavaFxTimerFactory()) { delta -> tick(delta) }

    fun playOrPause() {
        if (isPlaying.value) pause()
        else play()
        isPlaying.value = !isPlaying.value
    }

    fun tick(ms: Long) {
        val text = String.format("%010d", ms)
        gametime.set(text)
    }

    fun play() {
        clock.play()
    }

    fun pause() {
        clock.pause()
    }

    companion object {
        const val DEFAULT_WIDTH_TO_HEIGHT = 1.7777
    }
}