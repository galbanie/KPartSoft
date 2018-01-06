package com.github.galbanie

import com.github.galbanie.controllers.LoginController
import com.github.galbanie.views.LoginScreenView
import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import tornadofx.*

class PartSoftApp: App(LoginScreenView::class, PartSoftStyles::class, PartSoftScope()){
    val loginController: LoginController by inject()
    init {

    }
    override fun start(stage: Stage) {
        super.start(stage)
        //FX.primaryStage.icons += resources.image("/com/github/galbanie/checkmark_on_circle.png")
        loginController.init()
    }
    //override fun createPrimaryScene(view: UIComponent) = Scene(view.root, 1024.0, 768.0)
    override fun onBeforeShow(view: UIComponent) {
        configuration()
    }

    private fun configuration(){

    }
}

/**
 * The main method is needed to support the mvn jfx:run goal.
 */
fun main(args: Array<String>) {
    Application.launch(PartSoftApp::class.java, *args)
}