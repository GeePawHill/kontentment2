package org.geepawhill.kontentment.app

import javafx.beans.property.SimpleObjectProperty
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.paint.Color

class Skin {
    val normalBackground = SimpleObjectProperty(DEFAULT_NORMAL_BACKGROUND)

    companion object {
        val DEFAULT_NORMAL_BACKGROUND = Background(BackgroundFill(Color.DARKGRAY, null, null))
    }
}