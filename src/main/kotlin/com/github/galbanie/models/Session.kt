package com.github.galbanie.models

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*

/**
 * Created by Galbanie on 2017-10-09.
 */
class Session {
    val idProperty = SimpleIntegerProperty()
    var id by idProperty

    val userProperty = SimpleObjectProperty<User>()
    var user by userProperty

    val isUserProcessProperty = SimpleBooleanProperty(false)
    var isUserProcess by isUserProcessProperty
}

class SessionModel : ItemViewModel<Session>() {
    val id = bind(Session::idProperty)
    val user = bind(Session::userProperty)
    val isUserProcess = bind(Session::isUserProcessProperty)
}
