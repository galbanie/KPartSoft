package com.github.galbanie.views

import com.github.galbanie.models.Project
import tornadofx.*
import javafx.scene.control.ListView

/**
 * Created by Galbanie on 2017-10-09.
 */
class ProjectListView : View("Projects") {
    override val root = listview<Project> {

    }

}
