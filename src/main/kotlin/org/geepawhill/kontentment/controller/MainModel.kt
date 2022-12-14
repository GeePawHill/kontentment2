package org.geepawhill.kontentment.controller

import javafx.beans.property.SimpleBooleanProperty
import org.geepawhill.kontentment.OpenRndrThread
import org.geepawhill.kontentment.Script
import org.geepawhill.kontentment.announce.UiAnnouncer

class MainModel {

    val announcer = UiAnnouncer()
    var script = Script(announcer)
    val thread = OpenRndrThread(script)

    val canPlay = SimpleBooleanProperty(true)
    val canPause = SimpleBooleanProperty(false)

    init {
        announcer.subscribe(PlayingChange::class) { event -> onPlayingChange(event.isPlaying) }
    }

    fun onPlayingChange(isPlaying: Boolean) {
        canPlay.set(!isPlaying)
        canPause.set(isPlaying)
    }

    fun play() {
        script.resume()
    }

    fun pause() {
        script.pause()
    }


}