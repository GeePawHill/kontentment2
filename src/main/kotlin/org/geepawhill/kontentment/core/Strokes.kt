package org.geepawhill.kontentment.core

class Strokes : Step {
    override fun chooseView(chooser: DetailViewChooser) {
        chooser.chooseStrokes(this)
    }
}