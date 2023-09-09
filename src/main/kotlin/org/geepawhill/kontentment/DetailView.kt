package org.geepawhill.kontentment

import javafx.scene.Parent
import org.geepawhill.kontentment.core.DetailViewChooser
import org.geepawhill.kontentment.core.NoStep
import org.geepawhill.kontentment.core.Strokes
import org.geepawhill.kontentment.core.Typing
import org.geepawhill.kontentment.kwrappers.kstackpane
import tornadofx.*

class DetailView(val model: Model) : Fragment(), DetailViewChooser {
    val typingDetail = TypingDetailView(model)
    val strokesDetail = StrokesDetailView(model)
    val nostepDetail = NoStepDetailView(model)

    override val root: Parent = kstackpane(model) {
        minHeight = 300.0
        this += typingDetail
        this += strokesDetail
        this += nostepDetail
    }

    init {
        model.currentStep.value.chooseView(this)
        model.currentStep.addListener { _, _, new ->
            new.chooseView(this)
        }
    }

    override fun chooseStrokes(strokes: Strokes) {
        strokesDetail.root.show()
        typingDetail.root.hide()
        nostepDetail.root.hide()
    }

    override fun chooseTyping(typing: Typing) {
        strokesDetail.root.hide()
        typingDetail.root.show()
        nostepDetail.root.hide()
    }

    override fun chooseNoStep(noStep: NoStep) {
        strokesDetail.root.hide()
        typingDetail.root.hide()
        nostepDetail.root.show()
    }
}

