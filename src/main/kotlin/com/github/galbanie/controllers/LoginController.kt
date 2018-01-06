package com.github.galbanie.controllers

import com.github.galbanie.PartSoftWorkspace
import com.github.galbanie.views.LoginScreenView
import javafx.application.Platform
import javafx.scene.Scene
import tornadofx.*

/**
 * Created by Galbanie on 2017-10-09.
 */
class LoginController : Controller() {
    val loginScreen: LoginScreenView by inject()
    val workbench: PartSoftWorkspace by inject()
    fun init(){

    }

    fun tryLogin(username: String, password: String, remember: Boolean = false){
        runAsync {
            username == "admin" && password == "secret"
        } ui { successfulLogin ->

            if (successfulLogin) {
                loginScreen.clear()

                /*if (remember) {
                    with (config) {
                        set(USERNAME to username)
                        set(PASSWORD to password)
                        save()
                    }
                }*/

                showWorkbench()
            } else {
                showLoginScreen("Login failed. Please try again.", true)
            }
        }
    }

    fun showLoginScreen(message: String, shake: Boolean = false) {
        if (FX.primaryStage.scene.root != loginScreen.root) {
            FX.primaryStage.scene.root = loginScreen.root
            FX.primaryStage.sizeToScene()
            FX.primaryStage.centerOnScreen()
        }

        loginScreen.title = message

        Platform.runLater {
            loginScreen.username.requestFocus()
            if (shake) loginScreen.shakeStage()
        }
    }

    fun showWorkbench() {
        if (FX.primaryStage.scene.root != workbench.root) {
            FX.primaryStage.scene.root = workbench.root
            //FX.primaryStage.scene = Scene(workbench.root, 1024.0, 768.0)
            //FX.primaryStage.height = 768.0
            //FX.primaryStage.width = 1024.0
            FX.primaryStage.sizeToScene()
            FX.primaryStage.centerOnScreen()
        }
    }

    fun logout() {
        /*with (config) {
            remove(USERNAME)
            remove(PASSWORD)
            save()
        }*/

        showLoginScreen("Log in as another user")
    }
}