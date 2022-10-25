package org.geepawhill.kontentment

import org.geepawhill.kontentment.controller.MainView
import tornadofx.*

class Main : App(MainView::class)

fun main() {
    launch<Main>()
}