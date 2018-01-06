package com.github.galbanie

import com.github.galbanie.models.Session
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.FXCollections
import tornadofx.*

/**
 * Created by Galbanie on 2017-10-09.
 */
class PartSoftScope : Scope() {
    val session = SimpleObjectProperty<Session>(null)
}