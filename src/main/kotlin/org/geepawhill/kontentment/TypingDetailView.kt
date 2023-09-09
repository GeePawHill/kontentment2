package org.geepawhill.kontentment

import javafx.beans.property.SimpleStringProperty
import org.geepawhill.kontentment.core.Typing
import org.geepawhill.kontentment.kwrappers.klabel
import tornadofx.*

class TypingDetailView(model: Model) : Fragment() {

    val typingText = SimpleStringProperty("")

    override val root = vbox {
        klabel(model, "Typing")
        klabel(model, typingText)
    }

    fun load(typing: Typing) {
        typingText.set(typing.text)
    }


}
