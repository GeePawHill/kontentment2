package org.geepawhill.kontentment.core

interface DetailViewChooser {
    fun chooseStrokes(strokes: Strokes)
    fun chooseTyping(typing: Typing)
    fun chooseNoStep(noStep: NoStep)
}