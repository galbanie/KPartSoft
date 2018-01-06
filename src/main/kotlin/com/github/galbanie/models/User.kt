package com.github.galbanie.models

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.util.*

/**
 * Created by Galbanie on 2017-10-09.
 */
class User(id : Int, username : String, password : String) {
    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty

    val usernameProperty = SimpleStringProperty(username)
    var username by usernameProperty

    val passwordProperty = SimpleStringProperty(password)
    var password by passwordProperty

    val passwordSaltProperty = SimpleStringProperty()
    var passwordSalt by passwordSaltProperty

    val firstNameProperty = SimpleStringProperty()
    var firstName by firstNameProperty

    val lastNameProperty = SimpleStringProperty()
    var lastName by lastNameProperty

    val emailProperty = SimpleStringProperty()
    var email by emailProperty

    val accountActiveProperty = SimpleBooleanProperty(false)
    var accountActive by accountActiveProperty

    val dateExpirationProperty = SimpleObjectProperty<Date>()
    var dateExpiration by dateExpirationProperty

    val dateCreationProperty = SimpleObjectProperty<Date>()
    var dateCreation by dateCreationProperty

    val dateModifiedProperty = SimpleObjectProperty<Date>()
    var dateModified by dateModifiedProperty
}

class UserModel : ItemViewModel<User>() {
    val id = bind(User::idProperty)
    val username = bind(User::usernameProperty)
    val password = bind(User::passwordProperty)
    val passwordSalt = bind(User::passwordSaltProperty)
    val firstName = bind(User::firstNameProperty)
    val lastName = bind(User::lastNameProperty)
    val email = bind(User::emailProperty)
    val accountActive = bind(User::accountActiveProperty)
    val dateExpiration = bind(User::dateExpirationProperty)
    val dateCreation = bind(User::dateCreationProperty)
    val dateModified = bind(User::dateModifiedProperty)
}

