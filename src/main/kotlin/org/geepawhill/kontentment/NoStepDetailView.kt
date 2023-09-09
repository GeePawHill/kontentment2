package org.geepawhill.kontentment

import org.geepawhill.kontentment.kwrappers.klabel
import tornadofx.*

class NoStepDetailView(model: Model) : Fragment() {
    override val root = vbox {
        klabel(model, "No Step Detail")
    }
}
