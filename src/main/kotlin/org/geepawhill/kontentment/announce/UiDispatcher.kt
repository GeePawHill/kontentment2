package org.geepawhill.kontentment.announce

import javafx.application.Platform
import kotlin.reflect.KClass
import kotlin.reflect.full.isSuperclassOf

class UiDispatcher<T : Announcement>(val clazz: KClass<*>, val handler: (announcement: T) -> Unit) : Dispatcher {
    override fun dispatch(announcement: Announcement) {
        val aClazz = announcement::class
        if (clazz.isInstance(announcement) || clazz.isSuperclassOf(aClazz)) {
            val downcast = announcement as T
            Platform.runLater {
                handler(downcast)
            }
        }
    }
}