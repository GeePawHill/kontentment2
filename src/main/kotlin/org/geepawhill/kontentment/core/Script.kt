package org.geepawhill.kontentment.core

class Script {
    fun draw(animationTimeMs: Long, drawer: Drawer) {
        val text = String.format("%010d", animationTimeMs)
        drawer.blank()
        drawer.text(text)
    }
}