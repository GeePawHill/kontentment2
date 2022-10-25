package org.geepawhill.kontentment

import org.geepawhill.kontentment.announce.UiAnnouncer

class MainModel {

    val announcer = UiAnnouncer()
    var script = Script()
    val thread = OpenRndrThread(script, announcer)

    init {
        thread.start()
    }

    fun play() {
        thread.play()
    }
}