package org.geepawhill.kontentment.announce

import kotlin.reflect.KClass

interface Announcer {
    fun <T : Announcement> announce(announcement: T)
    fun <T : Announcement> subscribe(clazz: KClass<T>, handler: (announcement: T) -> Unit)
}