package org.geepawhill.kontentment.core

class Typing : Step {
    override fun chooseView(chooser: DetailViewChooser) {
        chooser.chooseTyping(this)
    }
}