package org.geepawhill.kontentment

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import org.geepawhill.kontentment.core.Clock
import org.geepawhill.kontentment.core.Drawer
import org.geepawhill.kontentment.core.NoStep
import org.geepawhill.kontentment.core.NullDrawer
import org.geepawhill.kontentment.core.Script
import org.geepawhill.kontentment.core.Step

class Model {

    val isPlaying = SimpleBooleanProperty(false)
    var playTime = 0L
    val gametime = SimpleStringProperty("000000000")
    val windowing = WindowingModel()
    val presentationWidthToHeight = SimpleDoubleProperty(DEFAULT_WIDTH_TO_HEIGHT)
    val script = SimpleObjectProperty(Script())

    val currentStep = SimpleObjectProperty<Step>(NoStep())

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
        script.value.draw(ms, drawer)
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