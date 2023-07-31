package org.geepawhill.kontentment

import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.control.Label
import javafx.scene.control.ToolBar
import javafx.scene.layout.Region

class WindowingModel {
    val isCanvasPopped = SimpleBooleanProperty(false)
    val isCanvasFullScreen = SimpleBooleanProperty(false)
    val skin = Skin()

    fun normalRegion(region: Region) {
        region.backgroundProperty().bind(skin.normalBackground)
    }

    fun normalLabel(label: Label) {
        label.fontProperty().bind(skin.normalFont)
        label.textFillProperty().bind(skin.normalTextFill)
    }

    fun normalToolbar(toolBar: ToolBar) {
        toolBar.backgroundProperty().bind(skin.normalBackground)
    }
}
