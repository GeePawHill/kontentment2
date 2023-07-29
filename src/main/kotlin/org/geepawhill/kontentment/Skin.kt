package org.geepawhill.kontentment

import javafx.beans.property.SimpleObjectProperty
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.paint.Color
import javafx.scene.text.Font

class Skin {
    val normalFont = SimpleObjectProperty(DEFAULT_NORMAL_FONT)
    val normalTextFill = SimpleObjectProperty(DEFAULT_NORMAL_TEXTFILL)
    val normalBackground = SimpleObjectProperty(DEFAULT_NORMAL_BACKGROUND)

    fun select(which: Int) {
        if (which == 0) {
            normalFont.set(DEFAULT_NORMAL_FONT)
            normalTextFill.set(DEFAULT_NORMAL_TEXTFILL)
            normalBackground.set(DEFAULT_NORMAL_BACKGROUND)
        } else {
            normalFont.set(ALTERNATE_NORMAL_FONT)
            normalTextFill.set(ALTERNATE_NORMAL_TEXTFILL)
            normalBackground.set(ALTERNATE_NORMAL_BACKGROUND)
        }
    }

    companion object {
        val DEFAULT_NORMAL_BACKGROUND = Background(BackgroundFill(Color.DARKGRAY, null, null))
        val ALTERNATE_NORMAL_BACKGROUND = Background(BackgroundFill(Color.ORCHID, null, null))
        val DEFAULT_NORMAL_FONT = Font.font(20.0)!!
        val ALTERNATE_NORMAL_FONT = Font.font(24.0)!!
        val DEFAULT_NORMAL_TEXTFILL = Color.BLACK!!
        val ALTERNATE_NORMAL_TEXTFILL = Color.WHITE!!
    }
}