package org.geepawhill.kontentment

import javafx.beans.property.SimpleDoubleProperty

class Model {
    val windowing = WindowingModel()
    val presentationWidthToHeight = SimpleDoubleProperty(DEFAULT_WIDTH_TO_HEIGHT)

    companion object {
        const val DEFAULT_WIDTH_TO_HEIGHT = 1.7777
    }
}