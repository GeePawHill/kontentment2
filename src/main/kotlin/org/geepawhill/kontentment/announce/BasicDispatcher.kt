package org.geepawhill.kontentment.announce

import kotlin.reflect.KClass
import kotlin.reflect.full.isSuperclassOf

class BasicDispatcher<T : Announcement>(val clazz: KClass<*>, val handler: (announcement: T) -> Unit) : Dispatcher {
    override fun dispatch(announcement: Announcement) {
        val aClazz = announcement::class
        if (clazz.isInstance(announcement) || clazz.isSuperclassOf(aClazz)) {
            val downcast = announcement as T
            handler(downcast)
        }
    }
}