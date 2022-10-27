package org.geepawhill.kontentment.announce

import kotlin.reflect.KClass

class CollectingAnnouncer : Announcer {
    val announcements = mutableListOf<Announcement>()

    override fun <T : Announcement> announce(announcement: T) {
        announcements += announcement
    }

    override fun <T : Announcement> subscribe(clazz: KClass<T>, handler: (announcement: T) -> Unit) {
        throw NotImplementedError("SinkAnnouncer does not forward announcements.")
    }
}