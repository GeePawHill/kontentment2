package org.geepawhill.kontentment.announce

import kotlin.reflect.KClass

class UiAnnouncer : Announcer {
    val dispatchers = mutableListOf<Dispatcher>()

    override fun <T : Announcement> announce(announcement: T) {
        dispatchers.forEach { it.dispatch(announcement) }
    }

    override fun <T : Announcement> subscribe(clazz: KClass<T>, handler: (announcement: T) -> Unit) {
        val dispatcher = UiDispatcher(clazz, handler)
        dispatchers += dispatcher
    }
}