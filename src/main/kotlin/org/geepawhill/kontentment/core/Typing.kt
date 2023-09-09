package org.geepawhill.kontentment.core

class Typing(text: String = "") : Step {

    var text: String = text

    override fun chooseView(chooser: DetailViewChooser) {
        chooser.chooseTyping(this)
    }
}