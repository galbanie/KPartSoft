package com.github.galbanie

import javafx.scene.paint.Color
import javafx.scene.paint.CycleMethod
import javafx.scene.paint.LinearGradient
import javafx.scene.paint.Stop
import javafx.scene.text.FontWeight
import tornadofx.*

class PartSoftStyles : Stylesheet() {
    companion object {
        val loginScreen by cssclass()
    }

    init {
        select(loginScreen) {
            padding = box(15.px)
            vgap = 7.px
            hgap = 10.px
        }
    }
}