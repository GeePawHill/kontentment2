package org.geepawhill.kontentment.announce

interface Dispatcher {
    fun dispatch(announcement: Announcement)
}