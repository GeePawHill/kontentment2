package org.geepawhill.kontentment

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import org.geepawhill.kontentment.core.Clock
import org.geepawhill.kontentment.core.Drawer
import org.geepawhill.kontentment.core.NullDrawer

class Model {

    val isPlaying = SimpleBooleanProperty(false)
    var playTime = 0L
    val gametime = SimpleStringProperty("000000000")
    val windowing = WindowingModel()
    val presentationWidthToHeight = SimpleDoubleProperty(DEFAULT_WIDTH_TO_HEIGHT)

    var drawer: Drawer = NullDrawer()

    private val clock = Clock(JavaFxPulseTimerFactory()) { delta -> tick(delta) }

    fun playOrPause() {
        if (isPlaying.value) pause()
        else play()
        isPlaying.value = !isPlaying.value
    }

    fun tick(ms: Long) {
        val text = String.format("%010d", ms)
        gametime.set(text)
        drawer.blank()
        drawer.text(text)
    }

    fun play() {
        clock.play()
    }

    fun pause() {
        clock.pause()
    }

    fun reAnnounceTime() {
        clock.reAnnounceTime()
    }

    companion object {
        const val DEFAULT_WIDTH_TO_HEIGHT = 1.7777
    }
}