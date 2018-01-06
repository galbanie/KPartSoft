package com.github.galbanie.views

import com.github.galbanie.controllers.LoginController
import javafx.scene.control.MenuButton
import javafx.scene.control.MenuItem
import javafx.scene.control.SeparatorMenuItem
import tornadofx.*

/**
 * Created by Galbanie on 2017-10-09.
 */
class UserMenuView : View() {
    val loginController: LoginController by inject()
    override val root = hbox {
        this += MenuButton().apply {
            /*graphic = imageview {
                //imageProperty().bind()
                fitHeight = 30.0
                fitWidth = 30.0
                isPreserveRatio = true
            }*/
            //textProperty().bind()
            items.addAll(
                    MenuItem("Profile").apply {
                        setOnAction {

                        }
                    },
                    MenuItem("Setting").apply {
                        setOnAction {

                        }
                    },
                    SeparatorMenuItem(),
                    MenuItem("Logout").apply {
                        setOnAction {
                            loginController.logout()
                        }
                    }
            )
        }
    }
}
