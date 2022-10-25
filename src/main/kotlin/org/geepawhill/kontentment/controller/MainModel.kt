package org.geepawhill.kontentment.controller

import javafx.beans.property.SimpleBooleanProperty
import org.geepawhill.kontentment.OpenRndrThread
import org.geepawhill.kontentment.Script
import org.geepawhill.kontentment.announce.UiAnnouncer

class MainModel {

    val announcer = UiAnnouncer()
    var script = Script()
    val thread = OpenRndrThread(script, announcer)

    val canPlay = SimpleBooleanProperty(true)
    val canPause = SimpleBooleanProperty(false)

    init {
        thread.start()
        announcer.subscribe(NowPlaying::class) { _ -> onPlayingChange(true) }
        announcer.subscribe(NowPaused::class) { _ -> onPlayingChange(false) }
    }

    fun onPlayingChange(isPlaying: Boolean) {
        canPlay.set(!isPlaying)
        canPause.set(isPlaying)
    }

    fun play() {
        thread.play()
    }

    fun pause() {
        thread.pause()
    }


}