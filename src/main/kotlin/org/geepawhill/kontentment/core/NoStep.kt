package org.geepawhill.kontentment.core

class NoStep : Step {
    override fun chooseView(chooser: DetailViewChooser) {
        chooser.chooseNoStep(this)
    }

}