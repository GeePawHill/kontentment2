package org.geepawhill.kontentment.app

import javafx.beans.property.SimpleDoubleProperty
import javax.print.SimpleDoc

class Model {
    val windowing = WindowingModel()
    val presentationWidthToHeight = SimpleDoubleProperty(DEFAULT_WIDTH_TO_HEIGHT)

    companion object {
        const val DEFAULT_WIDTH_TO_HEIGHT = 1.7777
    }
}